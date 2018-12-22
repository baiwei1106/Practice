#!/usr/bin/python  
# -*- coding: utf-8 -*-
__author__ = 'BG'  

import urllib, urllib2  
import cookielib
import re

class URP:

	def __init__(self,username,password):
		self.usr = username
		self.psw = password
		self.hosturl = 'http://urp.ab1.ren/'
		self.posturl = 'http://urp.ab1.ren/loginAction.do' 

		cj = cookielib.LWPCookieJar()  
		cookie_support = urllib2.HTTPCookieProcessor(cj)  
		opener = urllib2.build_opener(cookie_support, urllib2.HTTPHandler)  
		urllib2.install_opener(opener)  
		h = urllib2.urlopen(self.hosturl)  

		self.headers = {'User-Agent': 'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 10.0; WOW64; Trident/7.0; .NET4.0C; .NET4.0E; .NET CLR 2.0.50727; .NET CLR 3.0.30729; .NET CLR 3.5.30729)',
	        'Referer':'http://urp.ab1.ren/'} 
		self.postData = {'dllx':'dldl','zjh':self.usr,'mm':self.psw }
		self.postData = urllib.urlencode(self.postData)
		self.request = urllib2.Request( self.posturl, self.postData, self.headers )  

	def login(self):
		flag = False
		try:
			urllib2.urlopen(self.request)
			urllib2.urlopen('http://urp.ab1.ren/gradeLnAllAction.do?type=ln&oper=qbinfo&lnxndm').read()
		except urllib2.HTTPError, e:
			print '-----------------------------------------------------------'
			print 'Login Failed`s], maybe your username or password is error!'  %e.code
			print '-----------------------------------------------------------'
		else:
			print '-----------------------------------------------------------'
			print 'Login Successful'
			print '-----------------------------------------------------------'
			flag = True
		return flag
			
	def BXQ_score(self):
	  	self.score_html = urllib2.urlopen('http://urp.ab1.ren/bxqcjcxAction.do').read()

	def NJ_score(self):
	  	self.score_html = urllib2.urlopen('http://urp.ab1.ren/gradeLnAllAction.do?type=ln&oper=qbinfo&lnxndm').read()

	def TAB_score(self):
	  	self.score_html = urllib2.urlopen('http://urp.ab1.ren/reportFiles/student/cj_zwcjd_all.jsp').read()

	def FA_score(self):
	  	self.score_html = urllib2.urlopen('http://urp.ab1.ren/gradeLnAllAction.do?type=ln&oper=fainfo').read()
	
	def calc_gpa(self):
		reg = re.compile(r'<tr class="odd".*?>.*?<td.*?</td>.*?<td.*?</td>.*?<td.*?</td>.*?<td.*?</td>.*?<td align="center">\s*(\S+)\s*</td>.*?<td.*?</td>.*?<td align="center">.*?<p align="center">(.*?)&nbsp.*?</P>.*?</td>.*?<td.*?</td>.*?</tr>.*?',re.S)
		myItems = reg.findall(self.score_html)

		score = []
		credit = []
		sum = 0.0
		weight = 0.0

		for item in myItems:
			credit.append(item[0])
			score.append(item[1])
		
		for i in range(len(score)):
			try:
				we = float(credit[i])
				add = float(score[i])
				sum += add*we
				weight += we
			except:
				if score[i] == "优秀":	
					sum += 95.0*we
					weight += we
				elif score[i] == "良好":	
					sum += 85.0*we
					weight += we
				elif score[i] == "中等":	
					sum += 75.0*we
					weight += we
				elif score[i] == "及格":	
					sum += 60.0*we
					weight += we
				else:
					weight += we
		if weight == 0 :
			return
		print 'your GPA is ', sum/weight


	def save_html(self):
                 
		fout=open(self.usr+".html","w")  
		head = '<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">' 
		self.score_html = head + self.score_html.decode('GBK').encode('UTF-8')
		fout.write(self.score_html)  
		print '-----------------------------------------------------------'
		print 'The result was saved in score.html\nGod bless you!!!!!!'


def query_score():
	print 'Please input your username:'
	usr = raw_input()
	print 'Please input your password:'
	psw = raw_input()

	urp = URP(usr,psw)
	if urp.login() == True:
		print 'Please choose an mode:'
		print '1 - your score of each subject in this term'
		print '2 - all of your score in each term'
		print '3 - all of your score in a table'
		print '4 - all of your score by plan'

		choose = raw_input()
		if choose == '1':
			urp.BXQ_score()
		elif choose == '2':
			urp.NJ_score()
		elif choose == '3':
			urp.TAB_score()
		else:
			urp.FA_score()

		urp.save_html()
		urp.calc_gpa()
	else:
		return

if __name__ == '__main__':
	
	raw_input ('Press Enter to exit!')
