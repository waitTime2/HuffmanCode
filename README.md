# HuffmanCode
根据霍夫曼编码原理，基于Java实现简单的文件压缩。
## 基本原理
### 霍夫曼编码
霍夫曼编码属于信源编码的一种，能够对数据进行无损压缩。  
假设有符号“A，B，C，D，E，F”，编码步骤如下：

1. 统计各个符号的频数，并计算相应的频率。
2. 按照频率从小到大依次排序。
3. 选择最小的两个符号进行合并。假设A 和 B 合并为 G，其中P(G) = P(A)+P(B)
4. 将新合并的符号取代被合并的序列（G, C, D, E, F)
5. 重复2~4步骤，直至出现P(A) = 1.0

### 霍夫曼编码算法实现
按照树结构，构建霍夫曼树。根据霍夫曼编码特效，可以发现霍夫曼树属于最小堆。  
因此，算法步骤如下：
1. 统计数据中字符的频数，获得Map
2. 将各个字符及其频数封装成二叉树Node对象
3. 将所有节点是为最小堆，并加入到容器（可排序）中
4. 排序容器（从小到大）
5. 取出value最小的两棵树，构建成新的最小堆并重新放入到容器中
6. 重复步骤4~5，直至容器中只有一棵树（霍夫曼树）
7. 保存霍夫曼树
8. 利用二叉树遍历，对叶子结点进行编码，一般选择左路径编“0”，右路径编“1”
9. 保存编码表
10. 重新按字节读取数据，依次根据编码表进行编码，获取bit集（二进制字符串）
11. 将bit集按字节方式编码成byte数组
12. 将byte数组写入指定文件

### 霍夫曼解码算法
1. 读取数据，将每一byte转化为二进制字符串，获取bit流
2. 重建霍夫曼树
3. 从根节点出发，依次读取bit流，遇到“0”，跳转到左节点；遇到“1”，跳转到右节点
4. 遇到叶子节点，读取叶子节点的符号，回到根节点
5. 重读步骤3~4，直至读取完bit  
6. 保存解码数据到指定文件  

## 程序设计过程
### 数据模型（Model）
* 二叉树节点
```java
public class Node{
    private Character nodeKey;
    private Integer nodeValue;
    private Node leftChild;
    private Node rightChild;
    //....
}
```
* 二叉树
```java
public class BinaryTree {
    private String name;
    private Node rootNode;
    private int depth;
    private Integer nodeNum;
}
```
* 密码表
```java
public class PwdTable<K,V> implements Serializable {
    private String name;
    private Map<K,V> map;
}
```
### 设计模式（Design Mode）
本次尝试使用静态工厂生产模式，利用容器，实现类之间的解耦。  
每一个静态工厂，都包含一个容器，分别保存Model，给出create()，get()方法。
### 配置
编写配置类，进行统一配置，依次调度工厂类，生产对象，供服务类调用
### 服务类
####接口
文件压缩接口（FileCompression） 
* encoding（）
* decode（）
#### 实现类
霍夫曼编码（HuffmanCode）  