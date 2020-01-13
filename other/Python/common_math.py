# -*- coding: UTF-8 -*-

import math

# P=A*i*(1+i)^n/{[(1+i)^n]-1}
# P 每期还款本息 
# A 本金总额
# i 利率
# n 期数
def averageCapitalPlusInterest(A,i,n):
    x = pow(1+i,n)
    return A*i*x/(x-1)
	
# -------------------------- 实例 -------------------------------------

print("\n ---------- python 等额本息计算 ---------- \n" )	

# P=A*i*(1+i)^n/{[(1+i)^n]-1}
# P 每期还款本息 
# A 本金总额
# i 利率
# n 期数


print(" 本金总额 1800*0.99 月利率 0.08/12 期数 24")

capital = 1800*0.99
interst = 0.08/12
print(" 等额本息 %s" % averageCapitalPlusInterest(capital,interst,24))

capitalStr = input(" 请输入本金总额：")
capital = float(capitalStr)
interstStr = input(" 请输入月利率：")
interst = float(interstStr)
periodStr = input(" 请输入期数：")
period = float(periodStr)
print(" 等额本息 %s" % averageCapitalPlusInterest(capital,interst,period))

exit=input(" 输入回车后退出")



