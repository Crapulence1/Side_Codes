package textAdventure;

import textAdventure.Entities.Entity;

import java.util.ArrayList;
import java.util.Scanner;

public class MergeSort {
    public static void main(String[] args) {
//        ArrayList<Entity> data = getData();
//        Sort(data);
//        print(data);
    }

    public static void Sort(ArrayList<Entity> list){
        int length = list.size();
        if (length <=1){
            return;
        }
        ArrayList<Entity> left = new ArrayList<>();
        ArrayList<Entity> right = new ArrayList<>();
        int leftLength = length/2;
        int i = 0;
        for(; i < leftLength; i++){
            left.add(list.get(i));
        }
        for(; i < length; i++){
            right.add(list.get(i));
        }
        Sort(left);
        Sort(right);
        Merge(left, right, list);
    }

    public static void Merge(ArrayList<Entity> left, ArrayList<Entity> right, ArrayList<Entity> list){
        int leftCount = 0;
        int rightCount = 0;
        int listCount = 0;
        while(leftCount < left.size() && rightCount < right.size()){
            if(left.get(leftCount).getSpeed() > right.get(rightCount).getSpeed()){
                list.set(listCount, left.get(leftCount));
                leftCount++;
            } else {
                list.set(listCount, right.get(rightCount));
                rightCount++;
            }
            listCount++;
        }
        while(rightCount < right.size()){
            list.set(listCount, right.get(rightCount));
            rightCount++;
            listCount++;
        }
        while(leftCount < left.size()){
            list.set(listCount, left.get(leftCount));
            listCount++;
            leftCount++;
        }

    }

    public static void print(ArrayList<Entity> list){
        for(Entity string: list){
            System.out.println(string);
        }
    }

//    public static ArrayList<Entity> getData(){
//        ArrayList<String> input = new ArrayList<>();
//        Scanner in = new Scanner (System.in);
//        while (in.hasNextLine()){
//            input.add(in.nextLine());
//        }
//
//        return input;
//    }

}
