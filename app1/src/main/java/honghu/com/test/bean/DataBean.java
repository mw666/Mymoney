package honghu.com.test.bean;

import android.app.Application;

import java.util.List;

/**
 * Created by Administrator on 2017/5/7.
 */

public class DataBean extends Application{
    private DispatchList DispatchList;
    private List<WorkerMsg> WorkerList;

    public DataBean(DispatchList dispatchList, List<WorkerMsg> work_set) {
        this.DispatchList = dispatchList;
        this.WorkerList = work_set;
    }

    public DataBean.DispatchList getDispatchList() {
        return DispatchList;
    }

    public void setDispatchList(DataBean.DispatchList dispatchList) {
        DispatchList = dispatchList;
    }

    public List<WorkerMsg> getWorkerList() {
        return WorkerList;
    }

    public void setWorkerList(List<WorkerMsg> workerList) {
        WorkerList = workerList;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "DispatchList=" + DispatchList +
                ", WorkerList=" + WorkerList +
                '}';
    }


    public static class WorkerMsg{
        private String dispatchID;
        private String workerNo;

        public WorkerMsg(String dispatchID, String workerNo) {
            this.dispatchID = dispatchID;
            this.workerNo = workerNo;
        }

        public WorkerMsg() {
        }

        public String getDispatchID() {
            return dispatchID;
        }

        public void setDispatchID(String dispatchID) {
            this.dispatchID = dispatchID;
        }

        public String getWorkerNo() {
            return workerNo;
        }

        public void setWorkerNo(String workerNo) {
            this.workerNo = workerNo;
        }

        @Override
        public String toString() {
            return "WorkerMsg{" +
                    "dispatchID='" + dispatchID + '\'' +
                    ", workerNo='" + workerNo + '\'' +
                    '}';
        }
    }

    public static class DispatchList{
        // TODO: 2017/4/27
        private String Id;
        private String RegID;
        private String dispathTime;//派工时间
        private String Dispatcher;
        private String EventType;
        private String customerNo;
        private String customerName;
        private String customerType;
        private String tellNo;
        private String Address;
        private String TaskNo;
        private String taskDescribe;
        private String skillNo;
        private String skillSn;
        private String skillName;
        private String requestEndTime;
        private String appointmentTime;
        private String actualEndTime;
        private String finishDetial;
        private String custComments;
        private String checker;
        private String checkDetial;
        private String checkTime;
        private String dealState;
        private String IsEnd;
        private String abendReason;
        private String attachFormID1;
        private String attachFormID2;
        private String attachFormID3;
        private String receptionist;
        private String Estate;

        public DispatchList(String id, String regID, String dispathTime, String dispatcher, String eventType,
                            String customerNo, String customerName, String customerType, String tellNo,
                            String address, String taskNo, String taskDescribe, String skillNo, String skillSn,
                            String skillName, String requestEndTime, String appointmentTime, String actualEndTime,
                            String finishDetial, String custComments, String checker, String checkDetial,
                            String checkTime, String dealState, String isEnd, String abendReason, String attachFormID1,
                            String attachFormID2, String attachFormID3, String receptionist, String estate) {
            Id = id;
            RegID = regID;
            this.dispathTime = dispathTime;
            Dispatcher = dispatcher;
            EventType = eventType;
            this.customerNo = customerNo;
            this.customerName = customerName;
            this.customerType = customerType;
            this.tellNo = tellNo;
            Address = address;
            TaskNo = taskNo;
            this.taskDescribe = taskDescribe;
            this.skillNo = skillNo;
            this.skillSn = skillSn;
            this.skillName = skillName;
            this.requestEndTime = requestEndTime;
            this.appointmentTime = appointmentTime;
            this.actualEndTime = actualEndTime;
            this.finishDetial = finishDetial;
            this.custComments = custComments;
            this.checker = checker;
            this.checkDetial = checkDetial;
            this.checkTime = checkTime;
            this.dealState = dealState;
            IsEnd = isEnd;
            this.abendReason = abendReason;
            this.attachFormID1 = attachFormID1;
            this.attachFormID2 = attachFormID2;
            this.attachFormID3 = attachFormID3;
            this.receptionist = receptionist;
            Estate = estate;
        }

        public DispatchList() {
        }

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getRegID() {
            return RegID;
        }

        public void setRegID(String regID) {
            RegID = regID;
        }

        public String getDispathTime() {
            return dispathTime;
        }

        public void setDispathTime(String dispathTime) {
            this.dispathTime = dispathTime;
        }

        public String getDispatcher() {
            return Dispatcher;
        }

        public void setDispatcher(String dispatcher) {
            Dispatcher = dispatcher;
        }

        public String getEventType() {
            return EventType;
        }

        public void setEventType(String eventType) {
            EventType = eventType;
        }

        public String getCustomerNo() {
            return customerNo;
        }

        public void setCustomerNo(String customerNo) {
            this.customerNo = customerNo;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getCustomerType() {
            return customerType;
        }

        public void setCustomerType(String customerType) {
            this.customerType = customerType;
        }

        public String getTellNo() {
            return tellNo;
        }

        public void setTellNo(String tellNo) {
            this.tellNo = tellNo;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String address) {
            Address = address;
        }

        public String getTaskNo() {
            return TaskNo;
        }

        public void setTaskNo(String taskNo) {
            TaskNo = taskNo;
        }

        public String getTaskDescribe() {
            return taskDescribe;
        }

        public void setTaskDescribe(String taskDescribe) {
            this.taskDescribe = taskDescribe;
        }

        public String getSkillNo() {
            return skillNo;
        }

        public void setSkillNo(String skillNo) {
            this.skillNo = skillNo;
        }

        public String getSkillSn() {
            return skillSn;
        }

        public void setSkillSn(String skillSn) {
            this.skillSn = skillSn;
        }

        public String getSkillName() {
            return skillName;
        }

        public void setSkillName(String skillName) {
            this.skillName = skillName;
        }

        public String getRequestEndTime() {
            return requestEndTime;
        }

        public void setRequestEndTime(String requestEndTime) {
            this.requestEndTime = requestEndTime;
        }

        public String getAppointmentTime() {
            return appointmentTime;
        }

        public void setAppointmentTime(String appointmentTime) {
            this.appointmentTime = appointmentTime;
        }

        public String getActualEndTime() {
            return actualEndTime;
        }

        public void setActualEndTime(String actualEndTime) {
            this.actualEndTime = actualEndTime;
        }

        public String getFinishDetial() {
            return finishDetial;
        }

        public void setFinishDetial(String finishDetial) {
            this.finishDetial = finishDetial;
        }

        public String getCustComments() {
            return custComments;
        }

        public void setCustComments(String custComments) {
            this.custComments = custComments;
        }

        public String getChecker() {
            return checker;
        }

        public void setChecker(String checker) {
            this.checker = checker;
        }

        public String getCheckDetial() {
            return checkDetial;
        }

        public void setCheckDetial(String checkDetial) {
            this.checkDetial = checkDetial;
        }

        public String getCheckTime() {
            return checkTime;
        }

        public void setCheckTime(String checkTime) {
            this.checkTime = checkTime;
        }

        public String getDealState() {
            return dealState;
        }

        public void setDealState(String dealState) {
            this.dealState = dealState;
        }

        public String getIsEnd() {
            return IsEnd;
        }

        public void setIsEnd(String isEnd) {
            IsEnd = isEnd;
        }

        public String getAbendReason() {
            return abendReason;
        }

        public void setAbendReason(String abendReason) {
            this.abendReason = abendReason;
        }

        public String getAttachFormID1() {
            return attachFormID1;
        }

        public void setAttachFormID1(String attachFormID1) {
            this.attachFormID1 = attachFormID1;
        }

        public String getAttachFormID2() {
            return attachFormID2;
        }

        public void setAttachFormID2(String attachFormID2) {
            this.attachFormID2 = attachFormID2;
        }

        public String getAttachFormID3() {
            return attachFormID3;
        }

        public void setAttachFormID3(String attachFormID3) {
            this.attachFormID3 = attachFormID3;
        }

        public String getReceptionist() {
            return receptionist;
        }

        public void setReceptionist(String receptionist) {
            this.receptionist = receptionist;
        }

        public String getEstate() {
            return Estate;
        }

        public void setEstate(String estate) {
            Estate = estate;
        }

        @Override
        public String toString() {
            return "DispatchList{" +
                    "Id='" + Id + '\'' +
                    ", RegID='" + RegID + '\'' +
                    ", dispathTime='" + dispathTime + '\'' +
                    ", Dispatcher='" + Dispatcher + '\'' +
                    ", EventType='" + EventType + '\'' +
                    ", customerNo='" + customerNo + '\'' +
                    ", customerName='" + customerName + '\'' +
                    ", customerType='" + customerType + '\'' +
                    ", tellNo='" + tellNo + '\'' +
                    ", Address='" + Address + '\'' +
                    ", TaskNo='" + TaskNo + '\'' +
                    ", taskDescribe='" + taskDescribe + '\'' +
                    ", skillNo='" + skillNo + '\'' +
                    ", skillSn='" + skillSn + '\'' +
                    ", skillName='" + skillName + '\'' +
                    ", requestEndTime='" + requestEndTime + '\'' +
                    ", appointmentTime='" + appointmentTime + '\'' +
                    ", actualEndTime='" + actualEndTime + '\'' +
                    ", finishDetial='" + finishDetial + '\'' +
                    ", custComments='" + custComments + '\'' +
                    ", checker='" + checker + '\'' +
                    ", checkDetial='" + checkDetial + '\'' +
                    ", checkTime='" + checkTime + '\'' +
                    ", dealState='" + dealState + '\'' +
                    ", IsEnd='" + IsEnd + '\'' +
                    ", abendReason='" + abendReason + '\'' +
                    ", attachFormID1='" + attachFormID1 + '\'' +
                    ", attachFormID2='" + attachFormID2 + '\'' +
                    ", attachFormID3='" + attachFormID3 + '\'' +
                    ", receptionist='" + receptionist + '\'' +
                    ", Estate='" + Estate + '\'' +
                    '}';
        }
    }


}
