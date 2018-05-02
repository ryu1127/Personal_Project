# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models

# Create your models here.


class STUDENT(models.Model):
    name = models.CharField(max_length=20)
    std_num = models.CharField(max_length=10, primary_key=True)
    major = models.ForeignKey('MAJOR', on_delete=models.PROTECT)


class MAJOR(models.Model):
    name = models.CharField(max_length=30)
    mjr_num = models.CharField(max_length=4, primary_key=True)

    def __unicode__(self):
        return "%s" % self.name


class EMPLOYEE(models.Model):
    fname = models.CharField(max_length=20)
    minit = models.CharField(max_length=3)
    lname = models.CharField(max_length=20)
    ssn = models.CharField(max_length=10, primary_key = True)
    bdate = models.DateField()
    address = models.CharField(max_length=50)
    sex = models.CharField(max_length=1,choices=(('F','Female'),('M','Male')))
    salary = models.IntegerField()
    super_ssn = models.ForeignKey(
        'self',
        on_delete=models.PROTECT,
        blank=True,
        null=True,
    )
    dno = models.CharField(max_length=1)

    def __unicode__(self):
        return "%s" % self.name

class DEPARTMENT(models.Model):
    dname = models.CharField(max_length=20)
    dnumber = models.CharField(max_length=1 ,primary_key=True)
    mgr_ssn = models.CharField(max_length=10)
    mgr_start_date = models.DateField()

    def __unicode__(self):
        return "%s" % self.name

class DEPT_LOCATIONS(models.Model):
    dnumber = models.ForeignKey(
        'DEPARTMENT',
        on_delete=models.PROTECT,
        blank=True,
        null=True,
    )
    dlocation = models.CharField(max_length=20)

    def __unicode__(self):
        return "%s" % self.name
