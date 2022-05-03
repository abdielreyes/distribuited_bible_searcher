/*
 *  MIT License
 *
 *  Copyright (c) 2019 Michael Pogrebinsky - Distributed Systems & Cloud Computing with Java
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Aggregator {
  private WebClient webClient;

  public Aggregator() {
    this.webClient = new WebClient();
  }

  public List<String> sendTasksToWorkers(List<String> workersAddresses, List<String> taskss) {
    // CompletableFuture<String>[] futures = new
    // CompletableFuture[workersAddresses.size()];
    // CompletableFuture<String> [] futures = new
    // CompletableFuture[workersAddresses.size()];
    ArrayList<String> tasks = new ArrayList<String>(taskss);
    CompletableFuture<String> future1 = new CompletableFuture<String>();

    CompletableFuture<String> future2 = new CompletableFuture<String>();

    CompletableFuture<String> future3 = new CompletableFuture<String>();
    future1.complete("");
    future2.complete("");
    future3.complete("");
    ArrayList<String> out = new ArrayList<String>();
    System.out.println(tasks.size());
    for (String string : tasks) {
      System.out.println(string);
    }
    while (true) {
      try {
       // System.out.println(tasks.size()+" ,"+out.size());
      if (tasks.size() == 0 && future1.isDone() && future2.isDone() && future3.isDone()){
        break;
      }
          
        
        if (future1.isDone()) {
          String  o1 = future1.get();
          if(!out.contains(o1)) out.add(o1);
          //System.out.println("f2" + future2.get());
          if(tasks.size()>0){
            String w = tasks.get(tasks.size() - 1);
            tasks.remove(w);
            future1 = webClient.sendTask(workersAddresses.get(0), w.getBytes());
          }
          

        }
        if (future2.isDone()) {
          String  o1 = future2.get();
          if(!out.contains(o1)) out.add(o1);
          //System.out.println("f2" + future2.get());
          if(tasks.size()>0){
            String w = tasks.get(tasks.size() - 1);
          tasks.remove(w);
          future2 = webClient.sendTask(workersAddresses.get(1), w.getBytes());
          }
          

        }
        if (future3.isDone()) {
          String  o1 = future3.get();
          if(!out.contains(o1)) out.add(o1);
          //System.out.println("f3" + future3.get());
          if(tasks.size()>0){
            String w = tasks.get(tasks.size() - 1);
          tasks.remove(w);
          future3 = webClient.sendTask(workersAddresses.get(2), w.getBytes());
          }
          
        }
        
      } catch (Exception e) {
        // TODO: handle exception
      }


    }
    for (String string : out) {
      System.out.println(string);
    }
    // for (int i = 0; i < workersAddresses.size(); i++) {
    // String workerAddress = workersAddresses.get(i);
    // String task = tasks.get(i);
    //
    // byte[] requestPayload = task.getBytes();
    // futures[i] = webClient.sendTask(workerAddress, requestPayload);
    // }

    // List<String> results =
    // Stream.of(futures).map(CompletableFuture::join).collect(Collectors.toList());
    // List<String> results = Stream.of(future1);

    return Arrays.asList("");
  }
}