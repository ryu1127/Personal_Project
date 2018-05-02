# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render
from django.shortcuts import redirect

from .forms import *


def home(request):
    if request.method == 'POST':
        form = StudentForm(request.POST)
        if form.is_valid():
            form.save()
        else:
            return redirect('/home')
        return redirect('/sel')

    else:
        form = StudentForm()
        return render(request, 'home.html', {'form': form})


def sel(request):
    res = []
    for s in STUDENT.objects.all():
        res += [s]
    return render(request, 'sel.html', {'res': res})


def init(request):
    STUDENT.objects.all().delete()
    MAJOR.objects.all().delete()
    mjr1 = MAJOR(name="Software Engineering", mjr_num="01")
    mjr1.save()
    mjr2 = MAJOR(name="Computer Science", mjr_num="02")
    mjr2.save()
    std1 = STUDENT(name="zzang gu", std_num="201812345", major=mjr1)
    std1.save()
    std2 = STUDENT(name="Ezreal", std_num="201854321", major=mjr2)
    std2.save()

    return redirect('/sel')
