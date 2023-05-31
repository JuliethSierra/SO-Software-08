package models;

import java.math.BigInteger;
import java.util.ArrayList;

public class ProcessManager {

    private final int PROCESS_TIME = 1;
    private ArrayList<Process> inQueue, currentList, readyList;
    private ArrayList<PartitionReport> spaceList,processList, dispatchList, executionList, expirationList, finishedList, finishedPartition, noExecutionList;
    private ArrayList<Partition> partitions;
    private ArrayList<Condensation> condensations;

    public ProcessManager(){
        this.partitions = new ArrayList<>();
        this.inQueue = new ArrayList<>();
        this.currentList = new ArrayList<>();
        this.readyList = new ArrayList<>();
        this.dispatchList = new ArrayList<>();
        this.executionList = new ArrayList<>();
        this.expirationList = new ArrayList<>();
        this.finishedPartition = new ArrayList<>();
        this.finishedList = new ArrayList<>();
        this.processList = new ArrayList<>();
        this.condensations = new ArrayList<>();
        this.spaceList = new ArrayList<>();
        this.noExecutionList = new ArrayList<>();
    }

    public boolean isAlreadyProcessName(String name){
        for(Process process: inQueue){
            if(process.getName().equals(name))
                return true;
        }
        return false;
    }

    public void addToInQueue(Process process){
        this.inQueue.add(process);
    }

    public Object[][] getProcessListAsMatrixObject(ArrayList<Process> list){
        return this.parseArrayListToMatrixObject(list);
    }

    private Object[][] parseArrayListToMatrixObject(ArrayList<Process> list){
        int sizeQueue = list.size();
        Object[][] processList = new Object[sizeQueue][5];

        for(int i = 0; i < sizeQueue; i++){
            processList[i][0] = list.get(i).getName();
            processList[i][1] = list.get(i).getTime();
            processList[i][2] = list.get(i).getSize();
        }
        return processList;
    }

    public Object[][] getProcessListAsMatrixReportObject(ArrayList<PartitionReport> list){
        return this.parseArrayListToMatrixReportObject(list);
    }

    private Object[][] parseArrayListToMatrixReportObject(ArrayList<PartitionReport> list){
        int sizeQueue = list.size();
        Object[][] processList = new Object[sizeQueue][5];

        for(int i = 0; i < sizeQueue; i++){
            processList[i][0] = list.get(i).getPartitionName();
            processList[i][1] = list.get(i).getProcess().getName();
            processList[i][2] = list.get(i).getProcess().getTime();
            processList[i][3] = list.get(i).getProcess().getSize();
        }
        return processList;
    }

    public Object[][] getProcessListAsMatrixReportCon(ArrayList<Condensation> list){
        return this.parseArrayListToMatrixReportCon(list);
    }

    private Object[][] parseArrayListToMatrixReportCon(ArrayList<Condensation> list){
        int sizeQueue = list.size();
        Object[][] processList = new Object[sizeQueue][5];

        for(int i = 0; i < sizeQueue; i++){
            processList[i][0] = list.get(i).getName();
            processList[i][1] = list.get(i).getSize();
            processList[i][2] = list.get(i).getInitSize();
            processList[i][3] = list.get(i).getFinishSize();
        }
        return processList;
    }

    public Object[][] getProcessListAsMatrixReportPart(ArrayList<Partition> list){
        return this.parseArrayListToMatrixReportPart(list);
    }

    private Object[][] parseArrayListToMatrixReportPart(ArrayList<Partition> list){
        int sizeQueue = list.size();
        Object[][] processList = new Object[sizeQueue][5];

        for(int i = 0; i < sizeQueue; i++){
            processList[i][0] = list.get(i).getName();
            processList[i][1] = list.get(i).getSize();
        }
        return processList;
    }

    public Process getProcessInQueue(int indexDataInTable) {
        return this.inQueue.get(indexDataInTable);
    }

    public void updateProcessInQueue(Process newProcess, int indexDataInTable) {
        this.inQueue.set(indexDataInTable, newProcess);
    }
    public void deleteProcessInQueue(int indexDataInTable) {
        this.inQueue.remove(indexDataInTable);
    }

    public ArrayList<Process> getInQueue() {
        return inQueue;
    }

    public void setInQueue(ArrayList<Process> inQueue) {
        this.inQueue = inQueue;
    }

    public ArrayList<Process> getCurrentList() {
        return currentList;
    }

    public void setCurrentList(ArrayList<Process> currentList) {
        this.currentList = currentList;
    }

    public ArrayList<Process> getReadyList() {
        return readyList;
    }

    public void setReadyList(ArrayList<Process> readyList) {
        this.readyList = readyList;
    }

    public ArrayList<PartitionReport> getDispatchList() {
        return dispatchList;
    }

    public void setDispatchList(ArrayList<PartitionReport> dispatchList) {
        this.dispatchList = dispatchList;
    }

    public ArrayList<PartitionReport> getExecutionList() {
        return executionList;
    }

    public void setExecutionList(ArrayList<PartitionReport> executionList) {
        this.executionList = executionList;
    }

    public ArrayList<PartitionReport> getExpirationList() {
        return expirationList;
    }

    public void setExpirationList(ArrayList<PartitionReport> expirationList) {
        this.expirationList = expirationList;
    }

    public ArrayList<PartitionReport> getFinishedList() {
        return finishedList;
    }

    public void setFinishedList(ArrayList<PartitionReport> finishedList) {
        this.finishedList = finishedList;
    }

    public ArrayList<PartitionReport> getFinishedPartition() {
        return finishedPartition;
    }

    public void setFinishedPartition(ArrayList<PartitionReport> finishedPartition) {
        this.finishedPartition = finishedPartition;
    }

    public ArrayList<Partition> getPartitions() {
        return partitions;
    }

    public void setPartitions(ArrayList<Partition> partitions) {
        this.partitions = partitions;
    }

    public ArrayList<PartitionReport> getProcessList() {
        return processList;
    }

    public void setProcessList(ArrayList<PartitionReport> processList) {
        this.processList = processList;
    }

    public ArrayList<PartitionReport> getSpaceList() {
        return spaceList;
    }

    public void setSpaceList(ArrayList<PartitionReport> spaceList) {
        this.spaceList = spaceList;
    }

    public ArrayList<Condensation> getCondensations() {
        return condensations;
    }

    public void setCondensations(ArrayList<Condensation> condensations) {
        this.condensations = condensations;
    }

    public ArrayList<PartitionReport> getNoExecutionList() {
        return noExecutionList;
    }

    public void setNoExecutionList(ArrayList<PartitionReport> noExecutionList) {
        this.noExecutionList = noExecutionList;
    }

    public void cleanAllLists() {
        this.inQueue.clear();
        this.currentList.clear();
        this.readyList.clear();
        this.dispatchList.clear();
        this.executionList.clear();
        this.expirationList.clear();
        this.finishedList.clear();
        this.finishedPartition.clear();
        this.partitions.clear();
        this.processList.clear();
        this.condensations.clear();
        this.spaceList.clear();
        this.noExecutionList.clear();
    }

    public void initSimulation(){
        this.copyToCurrentProcess();
        this.initLoadToReady();
        this.fillPartitions();
        for (int i = 0; i < readyList.size(); i++) {
            for (int j = 0; j < partitions.size(); j++) {
                if ((readyList.get(i).getSize().compareTo(partitions.get(j).getSize()) == -1) || (readyList.get(i).getSize().compareTo(partitions.get(j).getSize()) == 0)) {
                    this.loadToDispatchQueue(new PartitionReport(partitions.get(j).getName(), new Process(readyList.get(i).getName(), readyList.get(i).getTime(), readyList.get(i).getSize())));
                    if (readyList.get(i).getTime().compareTo(BigInteger.valueOf(PROCESS_TIME)) == 1 || readyList.get(i).getTime().compareTo(BigInteger.valueOf(PROCESS_TIME)) == 0) {
                        this.loadToExecQueue(new PartitionReport(partitions.get(j).getName(), new Process(readyList.get(i).getName(), this.consumeTimeProcess(readyList.get(i)), readyList.get(i).getSize())));
                    } else {
                        this.loadToExecQueue(new PartitionReport(partitions.get(j).getName(), new Process(readyList.get(i).getName(), readyList.get(i).getTime(), readyList.get(i).getSize())));
                    }
                    if (!(readyList.get(i).getTime().compareTo(BigInteger.valueOf(0)) == 0)) {
                        if (readyList.get(i).getTime().compareTo(BigInteger.valueOf(PROCESS_TIME)) == 1) {
                            this.loadToExpirationQueue(new PartitionReport(partitions.get(j).getName(), new Process(readyList.get(i).getName(), this.consumeTimeProcess(readyList.get(i)), readyList.get(i).getSize())));
                            this.loadToReadyQueue(new Process(readyList.get(i).getName(), this.consumeTimeProcess(readyList.get(i)), readyList.get(i).getSize()));

                        } else {
                            this.loadToFinishedQueue(new PartitionReport(partitions.get(j).getName(), new Process(readyList.get(i).getName(), BigInteger.valueOf(0), readyList.get(i).getSize())));
                        }

                    }else {
                        this.loadToFinishedQueue(new PartitionReport(partitions.get(j).getName(), new Process(readyList.get(i).getName(), BigInteger.valueOf(0), readyList.get(i).getSize())));
                    }
                    j= partitions.size();
                }else {
                    this.loadToNoExecQueue(new PartitionReport(partitions.get(j).getName(),new Process(readyList.get(i).getName(), readyList.get(i).getTime(), readyList.get(i).getSize())));
                }
            }
        }
    }


    private BigInteger consumeTimeProcess(Process process) {
        return (process.getTime().subtract(BigInteger.valueOf(PROCESS_TIME)));
    }

    public void fillPartitions(){
        for (int i = 0; i < inQueue.size(); i++) {
            partitions.add(new Partition("Part"+String.valueOf(i+1), inQueue.get(i).getSize()));
        }
    }
    private void initLoadToReady() {

       /* inQueue.add(new Process("p1", new BigInteger("5"), new BigInteger("20")));
        inQueue.add(new Process("p2", new BigInteger("10"), new BigInteger("10")));
        inQueue.add(new Process("p3", new BigInteger("5"), new BigInteger("5")));
        inQueue.add(new Process("p4", new BigInteger("5"), new BigInteger("30")));
        inQueue.add(new Process("p5", new BigInteger("15"), new BigInteger("40")));
        inQueue.add(new Process("p6", new BigInteger("10"), new BigInteger("15")));*/
        readyList.addAll(inQueue);
    }


    public void copyToCurrentProcess(){
        currentList.addAll(inQueue);
    }

    private void loadToReadyQueue(Process process) {
        this.readyList.add(process);
    }

    private void loadToNoExecQueue(PartitionReport process) {
        this.noExecutionList.add(process);
    }

    private void loadToProcessList(PartitionReport process) {
        this.processList.add(process);
    }
    private void loadToDispatchQueue(PartitionReport partitionReport) {
        this.dispatchList.add(partitionReport);
    }
    private void loadToExecQueue(PartitionReport process) {
        this.executionList.add(process);
    }
    private void loadToExpirationQueue(PartitionReport process) {
        this.expirationList.add(process);
    }
    private void loadToFinishedQueue(PartitionReport process) {
        this.finishedList.add(process);
    }

    private void loadToCondensations(Condensation process) {
        this.condensations.add(process);
    }

    public BigInteger findMaxTime(){
        BigInteger num = new BigInteger("0");
        BigInteger num1 = new BigInteger("0");
        for (int i = 0; i < inQueue.size(); i++) {
            if(inQueue.get(i).getTime().compareTo(num)==1){
                num = inQueue.get(i).getTime();
            }
        }
        num1 = (num.divide(BigInteger.valueOf(PROCESS_TIME))).multiply(BigInteger.valueOf(inQueue.size()));
        num1 = num1.subtract(BigInteger.valueOf(1));
        return num1;
    }


    public void addCondensations(){
        int s=0;
        int s1=0;
        this.iniSpace();
        int count =0;
        int count1 =0;
        for (int i = 0; i <= findMaxTime().intValue(); i++) {
            if(processList.get(i).getProcess().getTime().compareTo(BigInteger.valueOf(PROCESS_TIME)) == 1 || processList.get(i).getProcess().getTime().compareTo(BigInteger.valueOf(PROCESS_TIME)) == 0){
                this.loadToProcessList(new PartitionReport(processList.get(i).getPartitionName(),new Process(processList.get(i).getProcess().getName(), processList.get(i).getProcess().getTime().subtract(new BigInteger("1")), processList.get(i).getProcess().getSize())));
            }else{
                this.loadToProcessList(new PartitionReport(processList.get(i).getPartitionName(),new Process(processList.get(i).getProcess().getName(),new BigInteger("0"), processList.get(i).getProcess().getSize())));
            }
            count ++;
            if (count == inQueue.size()){
                for (int j = i+1; j < processList.size(); j++) {
                    if(processList.get(j).getProcess().getTime().compareTo(BigInteger.valueOf(0)) == 0){
                        s+=processList.get(j).getProcess().getSize().intValue();

                        s1++;
                        if (s1 > 1 && j + 1 < processList.size() && (!(processList.get(j + 1).getProcess().getTime().compareTo(BigInteger.valueOf(0)) == 0))) {
                            loadToCondensations(new Condensation("C"+count1++, BigInteger.valueOf(s),processList.get(j-1).getProcess().getSize(),processList.get(j).getProcess().getSize().add(processList.get(j-1).getProcess().getSize())));
                            count = 0;
                            s1 = 0;
                            s = 0;
                        }
                        else if(j + 1 == processList.size() && s1 > 1){
                            loadToCondensations(new Condensation("C"+count1++, BigInteger.valueOf(s),processList.get(j-1).getProcess().getSize(),processList.get(j).getProcess().getSize().add(processList.get(j-1).getProcess().getSize())));
                            count = 0;
                            s1 = 0;
                            s = 0;
                        }
                    }else {
                        s=0;
                        s1=0;
                    }
                }
                s=0;
                count =0;

            }
        }
        spaceList.addAll(processList);
        for (int i = 0; i < spaceList.size(); i++) {
            if(spaceList.get(i).getProcess().getTime().compareTo(BigInteger.valueOf(0))==0){
                spaceList.get(i).setProcessList(new Process("Hueco", processList.get(i).getProcess().getTime(), processList.get(i).getProcess().getSize()));
            }
        }
    }



  /*  public void addCondensations(){
        int s=0;
        int s1=0;
        this.iniSpace();
        int count =0;
        int count1 =0;
        for (int i = 0; i <= findMaxTime().intValue(); i++) {
            if(processList.get(i).getProcess().getTime().compareTo(BigInteger.valueOf(PROCESS_TIME)) == 1 || processList.get(i).getProcess().getTime().compareTo(BigInteger.valueOf(PROCESS_TIME)) == 0){
                this.loadToProcessList(new PartitionReport(processList.get(i).getPartitionName(),new Process(processList.get(i).getProcess().getName(), processList.get(i).getProcess().getTime().subtract(new BigInteger("5")), processList.get(i).getProcess().getSize())));
            }else{
                this.loadToProcessList(new PartitionReport(processList.get(i).getPartitionName(),new Process(processList.get(i).getProcess().getName(),new BigInteger("0"), processList.get(i).getProcess().getSize())));
            }
            count ++;
            if (count == newInqueue.size()){
                for (int j = i+1; j < processList.size(); j++) {
                    if(processList.get(j).getProcess().getTime().compareTo(BigInteger.valueOf(0)) == 0){
                        s+=processList.get(j).getProcess().getSize().intValue();
                        s1++;
                        if (s1 > 1) {
                            loadToCondensations(new Condensation("C"+count1++, BigInteger.valueOf(s),processList.get(j-1).getProcess().getSize(),processList.get(j).getProcess().getSize().add(processList.get(j-1).getProcess().getSize())));
                            count = 0;
                            s1 = 0;
                            s = 0;
                        }
                    }else {
                        s=0;
                        s1=0;
                    }
                }
                s=0;
                count =0;
            }
        }
        spaceList.addAll(processList);
        for (int i = 0; i < spaceList.size(); i++) {
            if(spaceList.get(i).getProcess().getTime().compareTo(BigInteger.valueOf(0))==0){
                spaceList.get(i).setProcessList(new Process("Hueco", processList.get(i).getProcess().getTime(), processList.get(i).getProcess().getSize()));
            }
        }
    }*/

    public void iniSpace(){
        for (int i = 0; i < inQueue.size(); i++) {
            processList.add(new PartitionReport("part"+String.valueOf(i+1), inQueue.get(i)));
        }
    }
    public void cleanQueueList(){
        inQueue.clear();
    }



}
