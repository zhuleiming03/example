# -*- coding: UTF-8 -*-
 
from datetime import *

#得到当前日期和时间
def Common_GetToDay():
    return datetime.datetime.now()

#得到两个日期的日差
def Common_GetDateDiff(startDate,endDate):
    d = startDate.date() - endDate.date()
    return d.days

#给指定日期增添天数
def Common_AddDay(day,date):
    d = timedelta(day)
    return date + d

#给指定日期增添小时
def Common_AddHour(hour,date):
    h = timedelta(hours=hour)
    return date + h
	
# -------------------------- 实例 -------------------------------------

print("\n ---------- python 日期计算 ---------- \n" )	


Today = datetime.now()
print (" 当前时间 %s " % datetime.today())
print (" 当前时间 %s " % Today)
print (" 当前日期 %s " % Today.date())
print (" 当前时间 %s " % Today.time())
print (" 当前星期 %s " % Today.isoweekday())
TodayCalendar = Today.isocalendar()
print (" 当前 %s 年，第 %s 周，星期 %s" % (TodayCalendar[0],TodayCalendar[1],TodayCalendar[2]) )
print ("")	

LoanTime = datetime(2018, 1, 1, 18, 55, 59)
print (" 放款日是 %s" % LoanTime)

RelativeDate = datetime(2018, 1, 2)
print (" 相对日是 %s" % RelativeDate)

DuffDate = Common_GetDateDiff(RelativeDate,LoanTime)
print (" 放款日和相对日相差 %s 天" % DuffDate)

BillDay = Today.replace(day=RelativeDate.day)
print (" 当期账单日 %s " % BillDay)
print (" 账单日后6小时 %s " % Common_AddHour(6,BillDay))
print (" 账单日后1天 %s " % Common_AddDay(1,BillDay))

print ("")	
		

exit=input(" 输入回车后退出")




