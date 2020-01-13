# -*- coding: UTF-8 -*-

from decimal import *

# 获取运算环境
envir = getcontext()

# 设置7位有效数字精度(不设置数字精度，浮点数转十进制会有误差)
envir.prec = 7

# 缺省策略为 ROUND_HALF_EVEN 修改为四舍五入策略
envir.rounding = ROUND_HALF_UP

#数字转十进制
def initDecimal(val):
    #str = '%f' %val
    return envir.create_decimal(val)

#四舍五入，保留2位
def round2(val):
    obj = initDecimal(val)
    return Decimal(obj).quantize(Decimal('0.00'))

#四舍五入，保留4位
def round4(val):
    obj = initDecimal(val)
    return Decimal(obj).quantize(Decimal('0.0000'))

#四舍五入，保留6位
def round6(val):
    obj = initDecimal(val)
    return Decimal(obj).quantize(Decimal('0.000000'))

# -------------------------- 实例 -------------------------------------

print("\n ---------- python 四舍五入 ---------- \n" )

initValue=2.675

print(" 原始数据: %s \n" % initValue)

print(" round保留两位小数(精度有误),结果：%s \n " % round(initValue,2))

print(" decimal保留两位小数,结果：%s \n " % round2(initValue))

exit=input(" 输入回车后退出")




