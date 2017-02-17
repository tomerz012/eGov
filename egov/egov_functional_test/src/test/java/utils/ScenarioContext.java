package utils;

import java.io.*;

public class ScenarioContext implements Serializable {

    private String applicationNumber;

    private String assessmentNumber;

    private String dataScreenAssessmentNumber;

    private String voucherNumber;

    private String loaNumber;

    private String actualMessage;

    private String preambleNumber;

    private int isRemittance;

    private String consumerNumber;

    private String agendaNumber;

    private String meetingNumber;

    private String user;

    private String commAssessmentNumber;

    private String registrationNumber;

    private String caseFileNumber;

    public void setRegistrationNumber(String Number){
        this.registrationNumber = Number;
    }

    public String getRegistrationNumber(){
        return registrationNumber;
    }

    public String getCaseFileNumber() {
        return caseFileNumber;
    }

    public void setCaseFileNumber(String caseFileNumber) {
        this.caseFileNumber = caseFileNumber;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getConsumerNumber() {
        return consumerNumber;
    }

    public void setConsumerNumber(String consumerNumber) {
        this.consumerNumber = consumerNumber;
    }

    public int getIsRemittance() {
        return isRemittance;
    }

    public void setIsRemittance(int isRemittance) {
        this.isRemittance = isRemittance;
    }

    public String getLoaNumber() {
        return loaNumber;
    }

    public String getActualMessage() {
        return actualMessage;
    }

    public void setActualMessage(String actualMessage) {
        this.actualMessage = actualMessage;
    }

    public void setLoaNumber(String loaNumber) {
        this.loaNumber = loaNumber;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public String getAssessmentNumber() {
        return assessmentNumber;
    }

    public void setAssessmentNumber(String assessmentNumber) {
        this.assessmentNumber = assessmentNumber;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public String getDataScreenAssessmentNumber() {
        return dataScreenAssessmentNumber;
    }

    public void setDataScreenAssessmentNumber(String dataScreenAssessmentNumber) {
        this.dataScreenAssessmentNumber = dataScreenAssessmentNumber;
    }

    public void setPreambleNumber(String preambleNumber) {
        this.preambleNumber = preambleNumber;
    }

    public String getPreambleNumber() {
        return preambleNumber;
    }

    public void setAgendaNumber(String agendaNumber) {
        this.agendaNumber = agendaNumber;
    }

    public String getAgendaNumber() { return agendaNumber; }


    public void setMeetingNumber(String meetingNumber) { this.meetingNumber = meetingNumber; }

    public String getMeetingNumber() {
        return meetingNumber;
    }

    public String getCommAssessmentNumber() {
        return commAssessmentNumber;
    }

    public void setCommAssessmentNumber(String commAssessmentNumber) {
        this.commAssessmentNumber = commAssessmentNumber;
    }
}

