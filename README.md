# jvm-garbage-collector-log-parser
Java application that parses a JVM Garbage Collector Log and exports a CSV file that can be converted into a simple graph.

## Garbage Collector Log
GC's traces provided by JVM logs contain the following structures depending on the type of collection:
```
419,681: [GC [PSYoungGen: 208844K->12983K(593408K)] 494408K->305686K(943104K), 0,0219880 secs] [Times: user=0,04 sys=0,01, real=0,03 secs] 
419,703: [Full GC [PSYoungGen: 12983K->0K(593408K)] [ParOldGen: 292703K->245337K(349696K)] 305686K->245337K(943104K) [PSPermGen: 157091K->156743K(786432K)], 1,3205130 secs] [Times: user=2,21 sys=0,02, real=1,32 secs] 
```

## Parser's Functionality
This application parses a file that contains GC traces and extracts the different data stored in them. Then, a CSV file is created so it can be converted into a graph by means of external tools (i.e.: Microsoft Excel).

## Example
This project contains 2 files (`arranque.log.2019-05-02_1742.csv` and `arranque.log.2019-05-02_1742.xlsx`), the `CSV` is the output of this program given a real-life scenario (300MB log file) in which a server has a memory leak.
The `XLSX` file contains a table with the data and a graph that displays all the information. In this graph you can clearly see the memory leak and how it depends on the usage of the server (there's a period of 2 days with no increment that would correspond to a weekend).
A screenshot of the graph can be found [here](https://drive.google.com/open?id=18-pLg3wZkZYnk5TKcJHf-dfyGM8Eg1Y6).
