package com.example.ryu.callback_method_test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.inputmethodservice.Keyboard;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.WindowDecorActionBar;
import android.text.method.Touch;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter mediaFilter = new IntentFilter(Intent.ACTION_MEDIA_BUTTON);
        mediaFilter.setPriority(0);
        registerReceiver(headsetReceiver,mediaFilter);

    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        if(event.getAction()==KeyEvent.ACTION_DOWN&&event.getKeyCode()==KeyEvent.KEYCODE_VOLUME_DOWN){
            Toast.makeText(this,"volume down is pushed",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(event.getAction()==KeyEvent.ACTION_DOWN&&event.getKeyCode()== KeyEvent.KEYCODE_HEADSETHOOK){
            Toast.makeText(this,"headset is pushed",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(event.getKeyCode()==KeyEvent.KEYCODE_VOLUME_UP){
            Toast.makeText(this,"volume up is pushed",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(event.getAction()==KeyEvent.ACTION_DOWN&&event.getKeyCode()==KeyEvent.KEYCODE_HEADSETHOOK){
            Toast.makeText(this,"headset is hooked!",Toast.LENGTH_SHORT).show();
            return true;
        }
        else{
            Toast.makeText(this,"Cant recognize yet",Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private final BroadcastReceiver headsetReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String intentAction  = intent.getAction();
            if(!Intent.ACTION_MEDIA_BUTTON.equals(intentAction))
                return;
            KeyEvent event = (KeyEvent)intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
            int keyCode = event.getKeyCode();
            int action = event.getAction();
            Log.i("keycode", String.valueOf(keyCode));
            Log.i("action", String.valueOf(action));
        }
    };

    public void onDestroy(){
        super.onDestroy();
        unregisterReceiver(headsetReceiver);
    }

//-------------------------------------------------------------------------------------------------------------------
// 익명 내부 클래스의 임시 객체로 구현
//-------------------------------------------------------------------------------------------------------------------


//    public void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        View vw = new View(this);
//        vw.setOnTouchListener(new View.OnTouchListener(){
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction()==MotionEvent.ACTION_DOWN){
//                    Toast.makeText(MainActivity.this,"Touch Event Received",Toast.LENGTH_SHORT).show();
//                    return true;
//                }
//                return false;
//            }
//        });
//        setContentView(vw);
//    }

//-------------------------------------------------------------------------------------------------------------------
//익명 내부 클래스로 구현
//-------------------------------------------------------------------------------------------------------------------


//    public void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        View vw = new View(this);
//        vw.setOnTouchListener(TouchListener);
//        setContentView(vw);
//    }
//
//    View.OnTouchListener TouchListener = new View.OnTouchListener(){
//
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                //Pop up Toast and alert to client that Touch event is occur.
//                Toast.makeText(MainActivity.this, "Touch Event Received", Toast.LENGTH_SHORT).show();
//                return true;
//            }
//            return false;
//        }
//    };
//

//-------------------------------------------------------------------------------------------------------------------
//View에 Listener 구현
//-------------------------------------------------------------------------------------------------------------------


//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        MyView vw = new MyView(this);
//        vw.setOnTouchListener(vw);
//        setContentView(vw);
//    }
//
//    class MyView extends View implements View.OnTouchListener {
//
//        public MyView(Context context) {
//            super(context);
//        }
//
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                //Pop up Toast and alert to client that Touch event is occur.
//                Toast.makeText(MainActivity.this, "Touch Event Received", Toast.LENGTH_SHORT).show();
//                return true;
//            }
//            return false;
//        }
//    }


//-------------------------------------------------------------------------------------------------------------------
//Activity에 직접 Listener 구현
//-------------------------------------------------------------------------------------------------------------------


////Activity에서 바로 View.OnTouchListener를 구현한다
//public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
//    public void onCreate(Bundle saveInstanceState) {
//        super.onCreate(saveInstanceState);
//        View vw = new View(this);
//        //지정된 뷰 vw 에 터치 리스너를 설정한다
//        vw.setOnTouchListener(this);
//        //지정된 뷰를 보여준다
//        setContentView(vw);
//    }
//
//    @Override
//    //바로 액티비티 내에서 오버라이드 하여 사용한다
//    public boolean onTouch(View v, MotionEvent event) {
//        //ACTION_DOWN 즉 터치를 실행했을 때
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            //Pop up Toast and alert to client that Touch event is occur.
//            Toast.makeText(MainActivity.this, "Touch Event Received", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//        return false;
//    }


    //-------------------------------------------------------------------------------------------------------------------
    //Listener interface 를 이용한 방식
    //-------------------------------------------------------------------------------------------------------------------

//    protected void onCreate(Bundle saveInstanceState) {
//        super.onCreate(saveInstanceState);
//        View vw = new View(this);
//        //regist Listener Interface
//        vw.setOnTouchListener(TouchListener);
//        setContentView(vw);
//    }
//
//    //Listener class
//    class TouchListenerClass implements View.OnTouchListener {
//
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            //ACTION_DOWN 즉 터치를 실행했을 때
//            if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                //Pop up Toast and alert to client that Touch event is occur.
//                Toast.makeText(MainActivity.this, "Touch Event Received", Toast.LENGTH_SHORT).show();
//                return true;
//            }
//            return false;
//        }
//    }
//
//    //Create Listener Object
//    //and use in vw.setOnTouchListener
//    TouchListenerClass TouchListener = new TouchListenerClass();

    //-----------------------------------------------------------------------------------------------------------------
    //CallBack Method 를 이용한 방식
    //-----------------------------------------------------------------------------------------------------------------
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //새로운 뷰를 정의 한다
//        View view = new MyView(this);
//        // 그 뷰를 onCreate했는데 onDraw를 처리하지 않았으므로 아무것도 화면에 보여지지 않는다.
//        setContentView(view);
//    }
//
//    //MyView를 정의해준다.
//    class MyView extends View{
//        public MyView(Context context){
//            //Context를 상속받는것 외에는 아무것도 하지 않았으므로 비어있는 뷰가 생성되게 된다.
//            super(context);
//        }
//
//        //터치 이벤트를 발생시켜주는 부분이다.
//
//        //터치이벤트에 대해서 관여하고 파라미터로 모션이벤트 값을 받는 메서드이다
//        public boolean onTouchEvent(MotionEvent event){
//            //상위 클래스를 상속받는다
//            super.onTouchEvent(event);
//            //이벤트가 발생한게 화면을 눌렀을때로 한정했다.
//            if(event.getAction()==MotionEvent.ACTION_DOWN){
//                //토스트로 터치 이벤트에 대한 정보를 알려준다
//                Toast.makeText(MainActivity.this,"Touch Event Received",Toast.LENGTH_SHORT).show();
//                return true;
//            }
//            return false;
//        }
//    }
}
