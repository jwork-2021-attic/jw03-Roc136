### 1. 对代码工作原理的理解：

#### 1) 将排序算法隐写入图片

首先是在 `SteganographyFactory` 类中，通过 `getSteganography` 方法完成 1)编译目标排序算法、2)读取图片、3)将目标排序算法的二进制文件与图片混编得到新的数据、4)将新的数据写入新图片四个步骤。其中，将排序算法的二进制文件与图片混编是通过 `SteganographyEncoder` 类实现的，该类初始化时将图片的字节存入 `bufferImage` 中，并设置 `bitsFromColor` ，根据 `bitsFromColor` 设置 `mask` （即从RGB每种颜色的8位中取出几位来作为存储隐写数据的位，`mask` 用来做位运算时清空数据位）。写入数据时，遍历要写入的数据的 `bytes` ，将其8位拆分为 `bitsFromColor` 位的若干部分，分别写入每一个像素点的RGB值的8位中的低 `bitsFromColor` 位中，例如，某两个连续的像素点的RGB值分别为`#ADF4AD` , `#AAAAAA` ，与 `mask` 按位与后为 `#ACF4AC` , `#A8A8A8` (RGB值最高8位均为1，只考虑低24位)，如果要写入的数据为 `b'00011011` ，则写入过后的RGB值为 `#AEF5AC` , `#A8A8AA` ，将新的 RGB 值写入新的图片即可得到隐写图。这样写入后肉眼几乎无法看出，下面为对比图：

#ADF4AD：![](https://raw.githubusercontent.com/jwork-2021/jw03-Roc136/main/S191220112/resources/ADF4AD.png) 

#AEF5AC：![](https://raw.githubusercontent.com/jwork-2021/jw03-Roc136/main/S191220112/resources/AEF5AC.png) 

#AAAAAA：![](https://raw.githubusercontent.com/jwork-2021/jw03-Roc136/main/S191220112/resources/AAAAAA.png) 

#A8A8AA：![](https://raw.githubusercontent.com/jwork-2021/jw03-Roc136/main/S191220112/resources/A8A8AA.png) 

执行完上面的部分时，就将编译好的排序算法的类保存到了图片中。

#### 2） 从隐写图中加载排序算法

`SteganographyClassLoader` 类继承 `ClassLoader` 并重装 `findClass` 方法，使得该 `ClassLoader` 在寻找类时可以从给定的隐写图中解析得到类的二进制内容。解析隐写图时，只需根据写入时的方法，读取图片每个像素点的 RGB 值的各8位的低 `bitsFromColor` 位并将每8位合并到一个 byte 中，存入 `bytes` 数组即可。这里存在的问题就是读取时必须要知道写入时的顺序和位数，否则无法读取。读取到排序算法的类的字节后就可以在内存中构建该类了。

### 2. 两个排序算法的隐写图：

由于W02中两种排序算法依赖 `BubbleSorter` ，此处稍作一些修改。

快速排序：https://raw.githubusercontent.com/jwork-2021/jw03-Roc136/main/example.QuickSorter.png

选择排序：https://raw.githubusercontent.com/jwork-2021/jw03-Roc136/main/example.SelectSorter.png

### 3. 排序结果：

快速排序：

[![asciicast](https://asciinema.org/a/440329.svg)](https://asciinema.org/a/440329)

选择排序：

[![asciicast](https://asciinema.org/a/UxQJ9oWF2hBZidm2rcFSKTqLG.svg)](https://asciinema.org/a/UxQJ9oWF2hBZidm2rcFSKTqLG)

### 4. 另一位同学的图片

使用 [@skycloud36](https://github.com/skycloud36) 的图片，图片链接如下：

快速排序：https://pic.imgdb.cn/item/615dc6292ab3f51d91521a25.png

选择排序：https://pic.imgdb.cn/item/615dc7552ab3f51d915569da.png

得到的结果正确。