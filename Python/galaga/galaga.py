'''
Created on 2017. 7. 10.

@author: student
'''

from tkinter import *
from random import randint

NORMAL = 1
BLUE_KING = 2
GREEN_KING = 3
WIDTH = 1000
HEIGHT = 800

class Sprite():
    def __init__(self,img,x,y,canvas):
        self.img = img
        self.x = x
        self.y = y
        self.dx = 0               #필요한 상태값들을 생성자에서 초기화
        self.dy = 0
        self.canvas = canvas        # 캔버스는 처음 한번 받아둔걸 가지고 있어도 된다. 
        
    def move(self):
        self.x += self.dx
        self.y += self.dy
        
    def draw(self):
        self.canvas.create_image(self.x,self.y,image=self.img)
        
    def check(self,other):
        pass#적 객체가 총알 맞았는지 검사할 함수
    
class Ship(Sprite):
    def __init__(self,img,x,y,canvas):
        super().__init__(img,x,y,canvas)
    def move(self):
        super().move()

        self.dx =0
        self.dy =0
    def check(self):
        return False
        
class Bullet(Sprite):
    def __init__(self,img,x,y,canvas):
        super().__init__(img,x,y,canvas)
        self.dy = -10
    def check(self,other):
        x1 = self.x
        y1 = self.y
        x2 = self.x+self.img.width()
        y2 = self.y+self.img.height()
        
        x3 = other.x
        y3 = other.y
        x4 = other.x + other.img.width()
        y4 = other.y + other.img.height()
        
        if x2>=x3 and x1<=x4 and y2>=y3 and y1<=y4:
            return True
        if self.y<0:
            return True
        return False
        
class Enermy(Sprite):
    def __init__(self,img,x,y,canvas,level):
        super().__init__(img,x,y,canvas)
        self.left = x - 20
        self.right = x + 20
        self.dx = 2
        self.dy = 2
        self.level = level   #enermy - 1, blue_king - 2
        
        if level == NORMAL:
            self.life = 1
            
        elif level == BLUE_KING:
            self.life = 2
        elif level == GREEN_KING:
            self.life = 3
            
    def move(self):
        super().move()
        if self.level == GREEN_KING:
            self.dy =0
        if self.left > self.x or self.right < self.x:
            self.dx *= -1
        if self.y > HEIGHT:
            self.y = 0
        
        
    def check(self,other):
        x1 = self.x
        y1 = self.y
        x2 = self.x+self.img.width()
        y2 = self.y+self.img.height()
        
        x3 = other.x
        y3 = other.y
        x4 = other.x + other.img.width()
        y4 = other.y + other.img.height()
        
        if x2>=x3 and x1<=x4 and y2>=y3 and y1<=y4:
            self.life -= 1
        if self.y<0:
            self.life -= 1
        if self.life == 0 :
            if self.level==GREEN_KING:
                print("Green King's Dead!")
            elif self.level==BLUE_KING:
                print("Green King's Coming!")
                self.life = 10
                self.level = GREEN_KING
                self.img = PhotoImage(file='green_king.png')
                self.img = self.img.zoom(3, 3)
                return False
            return True   
        return False
#루프와 이벤트 처리기가 이 클래스에서 수행해야할 주요 기능        
class GalagaGame():
    def __init__(self,window):
        self.window = window
        self.canvas = Canvas(window,width=WIDTH, height = HEIGHT)
        self.canvas.pack()
        self.ship_img = PhotoImage(file="ship.png")
        self.bullet_img = PhotoImage(file="bullet.png")
        self.blue_king_img = PhotoImage(file="blue_king.png")
        self.enermy_img = PhotoImage(file = "enermy1.png")
        self.green_king_img = PhotoImage(file="green_king.png")
        self.bg_img = PhotoImage(file='bg.png')
        
        self.ship = Ship(self.ship_img,WIDTH/2,HEIGHT-40,self.canvas)
        self.window.bind('<Left>',self.keyLeft)
        self.window.bind('<Right>',self.keyRight)
        self.window.bind('<space>',self.keySpace)
        self.window.bind('<z>',self.Reset)
        self.window.bind('<Up>',self.keyUp)
        self.window.bind('<Down>',self.keyDown)
        
        self.bg_img = self.bg_img.zoom(2,2)
        self.enermyList = []
        self.bulletList = []
        self.count = 0
        self.Fire()


    def Reset(self,event):
        self.enermyList = []
        print("reset!+")

    def keySpace(self,event):
        x = self.ship.x
        y = self.ship.y
        bullet = Bullet(self.bullet_img,x-5,y,self.canvas)
#         bullet2 = Bullet(self.bullet_img,x+5,y,self.canvas)
        self.bulletList.append(bullet)
#         self.bulletList.append(bullet2)
    
    def Fire(self):
        x = self.ship.x
        y = self.ship.y
        bullet = Bullet(self.bullet_img,x-5,y,self.canvas)
#         bullet2 = Bullet(self.bullet_img,x+5,y,self.canvas)
        self.bulletList.append(bullet)
#         self.bulletList.append(bullet2)
        self.window.after(100,self.Fire)
        
    def keyUp(self,event):
        print("KeyUP!")
        if self.ship.y >0 :
            self.ship.dy = -10
        else :
            self.ship.y = HEIGHT
    def keyDown(self,event):
        if self.ship.y < HEIGHT:
            self.ship.dy = 10
        else :
            self.ship.y= 0
        
      
    def keyRight(self,event):
        if self.ship.x <WIDTH:
            self.ship.dx = 10
        else :
            self.ship.x = 0
        
    def keyLeft(self,event):
        if self.ship.x >0:    
            self.ship.dx = -10
        else:
            self.ship.x = WIDTH
    
    def loop(self):
        print("loop still spinning!")
        self.canvas.delete(ALL)
        
        self.canvas.create_image(0,0,image = self.bg_img)
        self.ship.move()
        self.ship.draw()
        self.makeEnermy()
        over = self.drawEnermy()
        if over:
            return True
        ch = self.drawBullet()
        if ch:
            self.canvas.create_image(0,0,image = self.bg_img)
            return

        self.window.after(10,self.loop)
        
    def drawEnermy(self):
        for enermy in self.enermyList:
            enermy.move()
            enermy.draw()

        
    def drawBullet(self):
        for bullet in self.bulletList:
            bullet.move()
            bullet.draw()
            for enermy in self.enermyList:
                if bullet.check(enermy):
                    self.bulletList.remove(bullet)
                    if enermy.check(bullet):
                        self.enermyList.remove(enermy)
                    break
                if enermy.check(self.ship):
                    print("game over")
                    return True
                    
        return False
    def makeEnermy(self):
        a = randint(1,100)
        if a>99 :
            obx = randint(1,WIDTH)
            oby = randint(100,HEIGHT)
            b = randint(1,100)
            if b>90 :
                enermy = Enermy(self.blue_king_img,obx,oby,self.canvas,BLUE_KING)
                print("blue king's coming!")
            else:
                enermy = Enermy(self.ship_img,obx,oby,self.canvas,NORMAL)
            self.enermyList.append(enermy)
#         self.window.after(100,self.loop)
    
        
window = Tk()


game = GalagaGame(window)

game.loop()
window.mainloop()
        
        
        
        
        
        
        
#         
# 
# class Control:
#     def __init__(self):
#         self.Bullet_list = []
#         self.ship_x = 20
#         self.ship_y = 580
# #         self.Bullet_location = [self.ship_x,self.ship_y-50]
#         self.ship_img = PhotoImage(file="ship.png")
#         self.bullet_img = PhotoImage(file="bullet.png")
#         self.Obstacle_list = []
# #         self.Obstacle = [self.obx,self.oby]
#     
#     
#     def KeyLeft(self,event):
#         if self.inBoardCheck()!=-1:
#             self.ship_x -= 10
#         
#         
#     def KeyRight(self,event):
#         if self.inBoardCheck()!=1:
#             self.ship_x += 10
#             
#             
#     def KeySpace(self,event):
# #         bullet_info = canvas.create_image(self.ship_x,self.ship_y-50,image=self.bullet_img)
#         bullet = [self.ship_x,self.ship_y]
#         self.Bullet_list.append(bullet)
#  
#     def fire(self):
# #         bullet_info = canvas.create_image(self.ship_x,self.ship_y-50,image=self.bullet_img)
#         bullet = [self.ship_x,self.ship_y]
#         self.Bullet_list.append(bullet)
#         window.after(20,self.fire)
#     
#     
# #     def moveBullet(self):
# #         for bl in self.Bullet_list:
# #             bl[1]-=10
# #         canvas.move(bullet,0,-10)
#     
#     def inBoardCheck(self):
# #         print(canvas.coords(ship))
#         startx = self.ship_x
#         if startx <30 :
#             return -1
#         elif startx >770:
#             return 1
#         else:
#             return 0
# 
# window = Tk()
# canvas = Canvas(window,width=800,height=HEIGHT)
# 
# canvas.pack()
# c = Control()
# 
# bullet_img = PhotoImage(file="bullet.png")
# ship_img = PhotoImage(file="ship.png")
# ship = canvas.create_image(20,580,image=ship_img)
# 
# 
# 
# 
# 
# window.bind('<Left>',c.KeyLeft)
# window.bind('<Right>',c.KeyRight)
# window.bind('<space>',c.KeySpace)
# class Sprite:
#     def draw(self):
#     
#         canvas.delete(ALL)
#         canvas.create_image(c.ship_x,c.ship_y,image = c.ship_img)
#         for ob in c.Obstacle_list:
#             canvas.create_image(ob[0],ob[1],image=c.ship_img)
#         a = randint(1,100)
#         if a>99 :
#             obx = randint(1,500)
#             oby = randint(100,400)
#             ob = [obx,oby]
#             c.Obstacle_list.append(ob)
#             print("Obstacle make!")
#         for bl in c.Bullet_list:
#             for ob in c.Obstacle_list:
#                 if bl[0]<ob[0]+20 and bl[0]>ob[0]-20 and bl[1]<ob[1]+20 and bl[1]>ob[1]-20 :
#                     c.Obstacle_list.remove(ob)
#             if bl[1]< 50 :
#                 c.Bullet_list.remove(bl)
#             bl[1]-=5
#             canvas.create_image(bl[0],bl[1],image = c.bullet_img)
#         
#         
#             
# 
#         window.after(10,s.draw)
# 
# s = Sprite()
# s.draw()
# c.fire()
# window.mainloop()
