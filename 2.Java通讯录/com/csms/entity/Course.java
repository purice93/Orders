package com.csms.entity;
 
/**
 * 
 * @program ������Ӧ���ݱ�ʵ��Ŀγ��࣬����dao�����ݿ������
 *
 */
public class Course {
    // �γ�ID��Ψһ��ʶ
    private String courID;
    // �γ�����
    private String courName;
    // �γ�����
    private int Csum;
    // ��ѡ������
    private int Cselected;
 
    
    public String getCourID() {
        return courID;
    }
 
    public void setCourID(String courID) {
        this.courID = courID;
    }
 
    public String getCourName() {
        return courName;
    }
 
    public void setCourName(String courName) {
        this.courName = courName;
    }

	public int getCsum() {
		return Csum;
	}

	public void setCsum(int csum) {
		Csum = csum;
	}

	public int getCselected() {
		return Cselected;
	}

	public void setCselected(int cselected) {
		Cselected = cselected;
	}

}