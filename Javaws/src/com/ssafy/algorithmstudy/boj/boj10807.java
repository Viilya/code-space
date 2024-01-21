import java.util.*;
public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int arr[] = new int[n];
        for(int k = 0 ; k < n; k ++){
            arr[k] = sc.nextInt();
        }
        int target = sc.nextInt();
        int count = 0;
        for(int k = 0 ; k < n ; k++){
            if(arr[k] == target){
                count += 1;
            }
        }
        
        System.out.println(count + "");
        
    }
}
