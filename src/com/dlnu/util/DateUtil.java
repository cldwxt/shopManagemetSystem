package com.dlnu.util;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
/*
 * ����Ϊ���Ժ�ʹ�÷���,���з����ķ������Ͷ���Ϊ�� java.util.Date ����ʹ��ʱ�����Լ�����Ҫ�������ڸ�ʽ������,��:
 * 
 * import java.text.SimpleDateFormat;
 * SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 * String todayBegin = simpleDateFormat.format(DateUtil.getDayBegin());
 * System.out.println(todayBegin );//������Ϊ2017-10-26 00:00:00
 */
/**
 * ���ڹ�����
 */
public class DateUtil {
	// ��ȡ����Ŀ�ʼʱ��
		public static java.util.Date getDayBegin() {
			Calendar cal = new GregorianCalendar();
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			return cal.getTime();
		}
	 
		// ��ȡ����Ľ���ʱ��
		public static java.util.Date getDayEnd() {
			Calendar cal = new GregorianCalendar();
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			return cal.getTime();
		}
	 
		// ��ȡ����Ŀ�ʼʱ��
		public static Date getBeginDayOfYesterday() {
			Calendar cal = new GregorianCalendar();
			cal.setTime(getDayBegin());
			cal.add(Calendar.DAY_OF_MONTH, -1);
			return cal.getTime();
		}
	 
		// ��ȡ����Ľ���ʱ��
		public static Date getEndDayOfYesterDay() {
			Calendar cal = new GregorianCalendar();
			cal.setTime(getDayEnd());
			cal.add(Calendar.DAY_OF_MONTH, -1);
			return cal.getTime();
		}
	 
		// ��ȡ����Ŀ�ʼʱ��
		public static Date getBeginDayOfTomorrow() {
			Calendar cal = new GregorianCalendar();
			cal.setTime(getDayBegin());
			cal.add(Calendar.DAY_OF_MONTH, 1);
			return cal.getTime();
		}
	 
		// ��ȡ����Ľ���ʱ��
		public static Date getEndDayOfTomorrow() {
			Calendar cal = new GregorianCalendar();
			cal.setTime(getDayEnd());
			cal.add(Calendar.DAY_OF_MONTH, 1);
			return cal.getTime();
		}
		
		// ��ȡn��ǰ�Ŀ�ʼʱ��
		public static Date getBeginDayOfNDay(int n) {
			Calendar cal = new GregorianCalendar();
			cal.setTime(getDayBegin());
			cal.add(Calendar.DAY_OF_MONTH, n);
			return cal.getTime();
		}
	 
		// ��ȡn��ǰ�Ľ���ʱ��
		public static Date getEndDayOfNDay(int n) {
			Calendar cal = new GregorianCalendar();
			cal.setTime(getDayEnd());
			cal.add(Calendar.DAY_OF_MONTH, n);
			return cal.getTime();
		}
	 
		// ��ȡ���ܵĿ�ʼʱ��
		@SuppressWarnings("unused")
		public static Date getBeginDayOfWeek() {
			Date date = new Date();
			if (date == null) {
				return null;
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
			if (dayofweek == 1) {
				dayofweek += 7;
			}
			cal.add(Calendar.DATE, 2 - dayofweek);
			return getDayStartTime(cal.getTime());
		}
	 
		// ��ȡ���ܵĽ���ʱ��
		public static Date getEndDayOfWeek() {
			Calendar cal = Calendar.getInstance();
			cal.setTime(getBeginDayOfWeek());
			cal.add(Calendar.DAY_OF_WEEK, 6);
			Date weekEndSta = cal.getTime();
			return getDayEndTime(weekEndSta);
		}
	 
		// ��ȡ���ܵĿ�ʼʱ��
		@SuppressWarnings("unused")
		public static Date getBeginDayOfLastWeek() {
			Date date = new Date();
			if (date == null) {
				return null;
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
			if (dayofweek == 1) {
				dayofweek += 7;
			}
			cal.add(Calendar.DATE, 2 - dayofweek - 7);
			return getDayStartTime(cal.getTime());
		}
	 
		// ��ȡ���ܵĽ���ʱ��
		public static Date getEndDayOfLastWeek() {
			Calendar cal = Calendar.getInstance();
			cal.setTime(getBeginDayOfLastWeek());
			cal.add(Calendar.DAY_OF_WEEK, 6);
			Date weekEndSta = cal.getTime();
			return getDayEndTime(weekEndSta);
		}
	 
		// ��ȡ���µĿ�ʼʱ��
		public static Date getBeginDayOfMonth() {
			Calendar calendar = Calendar.getInstance();
			calendar.set(getNowYear(), getNowMonth() - 1, 1);
			return getDayStartTime(calendar.getTime());
		}
	 
		// ��ȡ���µĽ���ʱ��
		public static Date getEndDayOfMonth() {
			Calendar calendar = Calendar.getInstance();
			calendar.set(getNowYear(), getNowMonth() - 1, 1);
			int day = calendar.getActualMaximum(5);
			calendar.set(getNowYear(), getNowMonth() - 1, day);
			return getDayEndTime(calendar.getTime());
		}
	 
		// ��ȡ���µĿ�ʼʱ��
		public static Date getBeginDayOfLastMonth() {
			Calendar calendar = Calendar.getInstance();
			calendar.set(getNowYear(), getNowMonth() - 2, 1);
			return getDayStartTime(calendar.getTime());
		}
	 
		// ��ȡ���µĽ���ʱ��
		public static Date getEndDayOfLastMonth() {
			Calendar calendar = Calendar.getInstance();
			calendar.set(getNowYear(), getNowMonth() - 2, 1);
			int day = calendar.getActualMaximum(5);
			calendar.set(getNowYear(), getNowMonth() - 2, day);
			return getDayEndTime(calendar.getTime());
		}
		
		// ��ȡn��ǰ�Ŀ�ʼʱ��
		public static Date getBeginDayOfNMonth(int n) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(getNowYear(), getNowMonth() - 1 +n, 1);
			return getDayStartTime(calendar.getTime());
		}
	 
		// ��ȡn��ǰ�Ľ���ʱ��
		public static Date getEndDayOfNMonth(int n) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(getNowYear(), getNowMonth() - 1 +n, 1);
			int day = calendar.getActualMaximum(5);
			calendar.set(getNowYear(), getNowMonth() - 1 +n, day);
			return getDayEndTime(calendar.getTime());
		}
	 
		// ��ȡ����Ŀ�ʼʱ��
		public static Date getBeginDayOfYear() {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, getNowYear());
			cal.set(Calendar.MONTH, Calendar.JANUARY);
			cal.set(Calendar.DATE, 1);
			return getDayStartTime(cal.getTime());
		}
	 
		// ��ȡ����Ľ���ʱ��
		public static java.util.Date getEndDayOfYear() {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, getNowYear());
			cal.set(Calendar.MONTH, Calendar.DECEMBER);
			cal.set(Calendar.DATE, 31);
			return getDayEndTime(cal.getTime());
		}
	 
		// ��ȡĳ�����ڵĿ�ʼʱ��
		public static Timestamp getDayStartTime(Date d) {
			Calendar calendar = Calendar.getInstance();
			if (null != d)
				calendar.setTime(d);
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			return new Timestamp(calendar.getTimeInMillis());
		}
	 
		// ��ȡĳ�����ڵĽ���ʱ��
		public static Timestamp getDayEndTime(Date d) {
			Calendar calendar = Calendar.getInstance();
			if (null != d)
				calendar.setTime(d);
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			return new Timestamp(calendar.getTimeInMillis());
		}
	 
		// ��ȡ��������һ��
		public static Integer getNowYear() {
			Date date = new Date();
			GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
			gc.setTime(date);
			return Integer.valueOf(gc.get(1));
		}
	 
		// ��ȡ��������һ��
		public static int getNowMonth() {
			Date date = new Date();
			GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
			gc.setTime(date);
			return gc.get(2) + 1;
		}
	 
		// ������������õ�������
		public static int getDiffDays(Date beginDate, Date endDate) {
			if (beginDate == null || endDate == null) {
				throw new IllegalArgumentException("getDiffDays param is null!");
			}
			long diff = (endDate.getTime() - beginDate.getTime())
					/ (1000 * 60 * 60 * 24);
			int days = new Long(diff).intValue();
			return days;
		}
	 
		// ������������õ��ĺ�����
		public static long dateDiff(Date beginDate, Date endDate) {
			long date1ms = beginDate.getTime();
			long date2ms = endDate.getTime();
			return date2ms - date1ms;
		}
	 
		// ��ȡ���������е��������
		public static Date max(Date beginDate, Date endDate) {
			if (beginDate == null) {
				return endDate;
			}
			if (endDate == null) {
				return beginDate;
			}
			if (beginDate.after(endDate)) {
				return beginDate;
			}
			return endDate;
		}
	 
		// ��ȡ���������е���С����
		public static Date min(Date beginDate, Date endDate) {
			if (beginDate == null) {
				return endDate;
			}
			if (endDate == null) {
				return beginDate;
			}
			if (beginDate.after(endDate)) {
				return endDate;
			}
			return beginDate;
		}
	 
		// ����ĳ�¸ü��ȵĵ�һ����
		public static Date getFirstSeasonDate(Date date) {
			final int[] SEASON = { 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4 };
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int sean = SEASON[cal.get(Calendar.MONTH)];
			cal.set(Calendar.MONTH, sean * 3 - 3);
			return cal.getTime();
		}
	 
		// ����ĳ�������¼��������
		public static Date getNextDay(Date date, int i) {
			Calendar cal = new GregorianCalendar();
			cal.setTime(date);
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) + i);
			return cal.getTime();
		}
	 
		// ����ĳ������ǰ���������
		public static Date getFrontDay(Date date, int i) {
			Calendar cal = new GregorianCalendar();
			cal.setTime(date);
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) - i);
			return cal.getTime();
		}
	 
		// ��ȡĳ��ĳ�µ�ĳ��ĳ�°������Ƭ���ڼ���(��������ļ���)
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public static List getTimeList(int beginYear, int beginMonth, int endYear,
				int endMonth, int k) {
			List list = new ArrayList();
			if (beginYear == endYear) {
				for (int j = beginMonth; j <= endMonth; j++) {
					list.add(getTimeList(beginYear, j, k));
				}
			} else {
				{
					for (int j = beginMonth; j < 12; j++) {
						list.add(getTimeList(beginYear, j, k));
					}
					for (int i = beginYear + 1; i < endYear; i++) {
						for (int j = 0; j < 12; j++) {
							list.add(getTimeList(i, j, k));
						}
					}
					for (int j = 0; j <= endMonth; j++) {
						list.add(getTimeList(endYear, j, k));
					}
				}
			}
			return list;
		}
	 
		// ��ȡĳ��ĳ�°�����Ƭ���ڼ���(ĳ���¼������������ڼ���)
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public static List getTimeList(int beginYear, int beginMonth, int k) {
			List list = new ArrayList();
			Calendar begincal = new GregorianCalendar(beginYear, beginMonth, 1);
			int max = begincal.getActualMaximum(Calendar.DATE);
			for (int i = 1; i < max; i = i + k) {
				list.add(begincal.getTime());
				begincal.add(Calendar.DATE, k);
			}
			begincal = new GregorianCalendar(beginYear, beginMonth, max);
			list.add(begincal.getTime());
			return list;
		}
		
		 /**
	     * �������ڻ�ȡ�������ܼ�
	     * @param datetime ����
	     * @return �ܼ�
	     */
	    public static String dateToWeek(String datetime) {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        String[] weekDays = {"������", "����һ", "���ڶ�", "������", "������", "������", "������"};
	        Calendar cal = Calendar.getInstance();
	        Date date;
	        try {
	            date = sdf.parse(datetime);
	            cal.setTime(date);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
	        return weekDays[w];
	    }


}
