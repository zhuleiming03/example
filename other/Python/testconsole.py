import datetime

#C#DateTime字符串
def Common_CSharpDateTimeStringToDateTime(str):
	return datetime.datetime.strptime(str, "%Y/%m/%d %H:%M:%S")
def Common_CSharpDateTimeStringToDate(str):
	return Common_CSharpDateTimeStringToDateTime(str).date()
def Common_CSharpDateTimeStringToTime(str):
	return Common_CSharpDateTimeStringToDateTime(str).time()
	
def Common_DateTimeToString(dt):
	return dt.strftime("%Y-%m-%d  %H:%M:%S")	
#得到当前日期	
def Common_Today():
	return datetime.datetime.now().date()
#得到两个日期的日差
def Common_GetDateDiff(startDate,endDate):
	return (startDate - endDate).days	
	
def Test(str):
	date=Common_Today()
	return isinstance(date,datetime)
	
exit=input("输入回车后退出")	