from django.forms import ModelForm
from .models import *


class StudentForm(ModelForm):
    class Meta:
        model = STUDENT
        fields = ['name', 'std_num', 'major']


class EmployeeForm(ModelForm):
    class Meta:
        model = EMPLOYEE
        fields = ['fname','minit','lname','ssn','bdate','address','sex','salary','super_ssn','dno']


class DepartmentForm(ModelForm):
    class Meta:
        model = DEPARTMENT
        fields = ['dname','dnumber','mgr_ssn','mgr_start_date']

class DeptLocationsForm(ModelForm):
    class Meta:
        model = DEPT_LOCATIONS
        fields = ['dnumber','dlocation']
