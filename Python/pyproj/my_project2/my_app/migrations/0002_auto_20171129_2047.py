# -*- coding: utf-8 -*-
# Generated by Django 1.11.4 on 2017-11-29 11:47
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('my_app', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='student',
            name='std_num',
            field=models.CharField(max_length=10, primary_key=True, serialize=False),
        ),
    ]
