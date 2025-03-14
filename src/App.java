import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class App {
    public static ArrayList<Integer> Random_Array_Generator(){
        ArrayList<Integer> Array = new ArrayList<Integer>();
        for(int index = 0; index <= 99 ;index++){
            int random_number = (int)(Math.random() * 101);
            Array.add(random_number);
        }
        return Array;
    }
    public static void writeArrayToFile(ArrayList<Integer> Array, String directory, String filename){
        try {
            FileWriter writer = new FileWriter(directory + File.separator + filename + ".txt");

            for(int index = 0 ; index <= Array.size() - 1; index++){
                writer.write(Integer.toString(Array.get(index)));
                if(index != Array.size() - 1){
                    writer.write("\n");
                }
                
            }
            writer.close();

        } catch(IOException e) {

        }
    }
    public static ArrayList<Integer> Array_From_File(String file_directory) {
        ArrayList<Integer> Array = new ArrayList<Integer>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file_directory))){
            String file_line;

            //use != instead of == or like .equals because if you used that the condition would check before you even execute the loop
            while ((file_line = reader.readLine()) != null) {
                Array.add(Integer.parseInt(file_line));
            }

        } catch (IOException e) {
            System.out.println("error in reading file");
        }

        return Array;
    }
    public static ArrayList<Integer> Bubble_Sort(ArrayList<Integer> array){
        ArrayList<Integer> sorted_array = new ArrayList<>(array); 
    
        boolean change_made; 
    
        do {
            change_made = false;  
            for (int index = 0; index < sorted_array.size() - 1; index++) {
                if (sorted_array.get(index) > sorted_array.get(index + 1)) {
    
                    int temp = sorted_array.get(index);
                    sorted_array.set(index, sorted_array.get(index + 1));
                    sorted_array.set(index + 1, temp);

                    change_made = true;
                }
            }
        } while (change_made);  
    
        return sorted_array;
    }
    
    
    public static void main(String[] args) throws Exception {
        String input;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            while (true){
                System.out.println("Enter \"random\" if you would like to generate a random list or enter \"sort\" if you'd like to sort an inputted text file");
                input = scanner.nextLine();
                switch(input){
                    case "random":
                        //initalization
                        String file_directory_random;
                        ArrayList<Integer> random_array = Random_Array_Generator();

                        //ask and check file path
                        while(true){
                            //receiving file directory and file name to create file path
                            System.out.println("please enter the directory of the folder where you would like your text file of random integers to be written to");
                            file_directory_random = scanner.nextLine();

                            //attempt to open folder to see if its valid
                            File folder = new File(file_directory_random);
                            if (!folder.isDirectory()){
                                System.out.println("EROR: Please enter a valid folder directory");
                                continue;
                            }
                            writeArrayToFile(random_array, file_directory_random, "random_numbers");
                            System.out.println("File has been written");
                            break;  
                        }
                        break; //end of case
                    case "sort":
                        String file_name;
                        String folder_directory_sorted;
                        while (true){
                            //check if folder is valid
                            while(true){
                                //receiving file directory and file name to create file path
                                System.out.println("please enter the directory of the folder where your text file of random integers is stored");
                                folder_directory_sorted = scanner.nextLine();
    
                                //attempt to open folder to see if its valid
                                File folder = new File(folder_directory_sorted);
                                if (!folder.isDirectory()){
                                    System.out.println("EROR: Please enter a valid folder directory");
                                    continue;
                                }
                                break;
                            }

                           //receiving file directory
                            while(true){
                                System.out.println("please enter the name of the file");
                                file_name = scanner.nextLine();

                                //attempt to open file to see if its valid
                                File text_file = new File(folder_directory_sorted + File.separator + file_name);
                                if (!text_file.exists()) {
                                    System.out.println("EROR: Please enter a valid file");
                                    continue;
                                }
                                break;
                            } 
                            break;
                        }
                        
                        //open file and convert to array
                        ArrayList<Integer> array_from_file = Array_From_File(folder_directory_sorted + File.separator + file_name);
                        

                        //sort array
                        ArrayList<Integer> sorted_array = new ArrayList<Integer>();
                        sorted_array = Bubble_Sort(array_from_file);
                        
                        

                        //rewrite original text file with sorted integers
                        writeArrayToFile(sorted_array,folder_directory_sorted, "sorted_numbers");
                        System.out.println("sorted numbers printed to file");
                        break; //end of case
                    default:
                        System.out.println("please enter a valid keyword");
                        continue;
                }
                break; //chat is this real?  
            }
            while(true) {
                System.out.println("Type in \"continue\" to restart the program or type in \"exit\" to close the program");
                input = scanner.nextLine();
                switch(input){
                    case "exit":
                        scanner.close();
                        System.exit(0);
                    case "continue":
                        break;
                    default:
                        System.out.println("Please enter a valid key word");
                        continue;
                }
                break;
            }
        }
    }   
}
