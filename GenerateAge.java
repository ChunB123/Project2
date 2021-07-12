package Assignment.Project2;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GenerateAge {

    static Scanner scanner = new Scanner(System.in);

    private interface List<T> {
        T get(int index) throws Exception;
        boolean add(T item) throws Exception;
        void add(int index, T item) throws Exception;
        T remove(int index) throws Exception;
        int size();
        void removeAll();
    }

    private class ArrayList<T> implements List<T> {

        T[] arr;
        int size;

        public ArrayList() {
            this.arr = (T[]) new Object[10];
            this.size=0;
            //new java.util.ArrayList<>();
        }

        private void grow(){
            T[] newArray=(T[]) new Object[arr.length*2];
            for(int i=0;i<arr.length;i++)
                newArray[i]=arr[i];
            arr=newArray;
        }

        @Override
        public T get(int index) throws Exception {
            if(index<0 || index>=size){
                throw new Exception("List index out of bounds");
            }
            return arr[index];
        }

        @Override
        public boolean add(T item) {
            if(size==arr.length){
                grow();
            }
            arr[size]=item;
            size++;
            return true;
        }

        @Override
        public void add(int index, T item) throws Exception {
            if(index<0 || index>=size){
                throw new Exception("List index out of bounds");
            }
            if(size==arr.length){
                grow();
            }
            for(int i=size;i>index;i--){
                arr[i]=arr[i-1];
            }
            arr[index]=item;
            size++;
        }

        @Override
        public T remove(int index) throws Exception {
            if(index<0 || index>=size){
                throw new Exception("List index out of bounds");
            }
            T result=arr[index];
            for(int i=index;i<size-1;i++){
                arr[i]=arr[i+1];
            }
            size--;
            return result;
        }

        @Override
        public int size() {
            return size;
        }

        public void removeAll(){
            this.arr = (T[]) new Object[10];
            this.size=0;
        }


    }

    private class LinkedList<T> implements List<T> {


        Node<T> head;
        int size;

        private class Node<T>{
            T data;
            Node<T> next;
            public Node(T data){
                this.data=data;
                next=null;
            }
        }

        public LinkedList() {
            head = null;
            size = 0;
        }

        @Override
        public T get(int index) throws Exception {
            if (index < 0 || index >= size) {
                throw new Exception("List index out of bounds");
            }
            Node<T> result = head;
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
            return result.data;
        }

        public boolean add(T item) {
            if (size == 0) {
                head = new Node<T>(item);
                size++;
                return true;
            }
            if (size > 0) {
                int itemCount = size;
                Node<T> newNode = head;
                while (itemCount > 1) {
                    newNode = newNode.next;
                    itemCount--;
                }
                newNode.next = new Node<T>(item);
                size++;
            }
            return false;
        }


        public void add(int index, T item) throws Exception {
            if (index > size || index < 0) {
                throw new Exception("Index is incorrect");
            }
            Node<T> newNode = head;
            while (index > 1) {
                newNode = newNode.next;
                index--;
            }
            Node<T> targetNode = new Node<T>(item);
            targetNode.next = newNode.next;
            newNode.next = targetNode;
            size++;
        }


        public T remove(int index) throws Exception {
            if (index > size || index < 0) {
                throw new Exception("Index is incorrect");
            }
            if (index == 0) {
                Node<T> targetNode = head.next;
                head = targetNode;
                size--;
                return targetNode.data;
            }

            Node<T> newNode = head;
            while (index > 1) {
                newNode = newNode.next;
                index--;
            }
            Node<T> targetNode = newNode.next;
            newNode.next = targetNode.next;
            size--;
            return targetNode.data;
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public void removeAll() {
            head = null;
            size = 0;
        }
    }


    private Properties readPropertiesFile(String fileName) throws Exception {
        FileInputStream fis = null;
        Properties prop = null;
        fis = new FileInputStream(fileName);
        prop = new Properties();
        prop.load(fis);

        fis.close();
        return prop;
    }

    private List getListAlgorithm (String algoName) {

        if (algoName.equalsIgnoreCase("Arraylist"))
            return new ArrayList();
        else if (algoName.equalsIgnoreCase("LinkedList"))
            return new LinkedList();
        else
            return null;
    }

    private List<DataPoint> storeData(List<DataPoint> list, String directory) {
        File[] files = new File(directory).listFiles();
        for (File file : files) {
            try{
                BufferedReader csvReader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                String row="";
                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");
                    // do something with the data
                    list.add(new DataPoint(data[0],data[1],Integer.parseInt(data[2]),data[3],Integer.parseInt(data[4])));
                }
                csvReader.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return list;
    }

    private Person getUserInput(){
        Person person=new Person();
        //1. Print the question
        System.out.println("Name of the person (or EXIT to quit):");
        //2. Get the answer
        String name=scanner.nextLine();
        //3. Check the user input
        if(name.equalsIgnoreCase("exit")){
            System.exit(0);
        }
        person.setName(name.substring(0, 1).toUpperCase() + name.substring(1));

        System.out.println("Gender (M/F):");
        String gender=scanner.nextLine();
        //Caution
        while(!(gender.equalsIgnoreCase("M")||gender.equalsIgnoreCase("F"))){
            System.out.println("Please type the Gender again (M/F):");
            gender=scanner.nextLine();
        }
        person.setGender(gender.toUpperCase());

        System.out.println("State of birth (two-letter state code):");
        String pattern = "AL|AK|AR|AZ|CA|CO|CT|DC|DE|FL|GA|HI|IA|ID|IL|IN|KS|KY|LA|MA|MD|ME|MI|MN|MO|MS|MT|NC|ND|NE|NH|NJ|NM|NV|NY|OH|OK|OR|PA|RI|SC|SD|TN|TX|UT|VA|VT|WA|WI|WV|WY|al|ak|ar|az|ca|co|ct|dc|de|fl|ga|hi|ia|id|il|in|ks|ky|la|ma|md|me|mi|mn|mo|ms|mt|nc|nd|ne|nh|nj|nm|nv|ny|oh|ok|or|pa|ri|sc|sd|tn|tx|ut|va|vt|wa|wi|wv|wy";
        String state=scanner.nextLine();
        Matcher m = Pattern.compile(pattern).matcher(state);
        while(!m.matches()){
            System.out.println("Please type the correct State of birth (two-letter state code):");
            state=scanner.nextLine();
            m = Pattern.compile(pattern).matcher(state);
        }
        person.setState(state.toUpperCase());
        return person;
    }

    private List<DataPoint> findPerson(Person input, List<DataPoint> list,List<DataPoint> result){
        for(int i=0;i<list.size();i++){
            try{
                if(list.get(i).getName().equals(input.getName())
                        &&list.get(i).getGender().equals(input.getGender())
                        &&list.get(i).getState().equals(input.getState())
                )
                {
                    result.add(list.get(i));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }

    private void generateOutput(List<DataPoint> result,List<DataPoint> targetList){
        if(result.size()==0)
            System.out.println("Such combination do not exist!");
        else{
            try{
                targetList.add(result.get(0));
                for(int i=1; i<result.size();i++){
                    if(result.get(i).getOccurrence()>targetList.get(0).getOccurrence()){
                        targetList.removeAll();
                        targetList.add(result.get(i));
                    }else if(result.get(i).getOccurrence()==targetList.get(0).getOccurrence()){
                        targetList.add(result.get(i));
                    }
                }
                int lowerBound=targetList.get(0).getYear();
                int upperBound=lowerBound;
                for(int i=0;i<targetList.size();i++){
                    if(targetList.get(i).getYear()>upperBound){
                        upperBound=targetList.get(i).getYear();
                    }
                    if(targetList.get(i).getYear()<lowerBound){
                        lowerBound=targetList.get(i).getYear();
                    }
                }

                String output=(2021-lowerBound)+"";
                if(lowerBound!=upperBound){
                    output=(2021-upperBound)+"-"+(2021-lowerBound);
                }
                DataPoint dataPoint=targetList.get(0);
                System.out.println(dataPoint.getName()+", born in "+dataPoint.getState()+" is most likely around "+output+" years old.");
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }


    public static void main(String args[]) {
        GenerateAge generateAge=new GenerateAge();

        Properties prop = null;
        List<DataPoint> list=null;
        try {
            prop = generateAge.readPropertiesFile("/Users/usfmichael/OneDrive - University of San Francisco/USF/CS112/IdeaJava/CS245Project/src/Assignment/Project2/info.properties");
            list=generateAge.getListAlgorithm(prop.getProperty("ListType"));
            //Data
            list=generateAge.storeData(list,prop.getProperty("Directory"));
        } catch (Exception e) {
            System.out.println("Config file error");
            System.exit(0);
        }


        while(true){
            Person input;
            try{
                //UserInput
                input=generateAge.getUserInput();
            }catch (Exception e){
                e.printStackTrace();
                break;
            }

            //Person input=new Person("Chloe","F","NY");
            List<DataPoint> result=generateAge.getListAlgorithm(prop.getProperty("ListType"));
            generateAge.findPerson(input,list,result);

            //print the result Ruby Ruth Willie
            generateAge.generateOutput(result,generateAge.getListAlgorithm(prop.getProperty("ListType")));

        }
        scanner.close();

    }
}
