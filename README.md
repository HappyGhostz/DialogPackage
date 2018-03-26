# DialogPackage
Android`DialogPackage` View`DialogFragment`

DialogPackage是对DialogFragment的进一步封装，采用Builder设计模式，支持链式调用，支持dialog动画，通过传入xml文件和view对象对diaolog为所欲为.

GIF演示效果
=====
![baidu](https://github.com/HappyGhostz/DialogPackage/blob/master/gif/customdialog.gif)

Start
=====
首先需要在项目根目录下的build.gradle文件中添加如下代码
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

然后在module下的build.gradle文件中添加依赖
```
dependencies {
	        compile 'com.github.HappyGhostz:DialogPackage:v1.0'
	}
```
框架分析
====
Builder模式
--------
网上看到的定义：将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示.

我的理解很简单：就是将我们对一个类的复杂设置，通过一个中间介质来实现，而本来的类专注实现功能就行，想一些数据的赋值，属性的设置都可以用这个中间介质来实现，
通过返回This对象实现链式的调用，实现复杂的数据设置，并使逻辑看起来简洁清晰！

DialogPackage分为两种模式：普通对话框；完全自定义对话框！
------
* 普通对话框：为遵循Material Design效果我在onCreateDialog()返回的是support包下的AlertDialog对象

如果你想要一个普通的对话框，可以这样实现
```
 new CustomDialog.Builder()
                .setTitle(title)
                .setIsDefaultDialog(true)
                .setMessage(message)
                .setCancelable(canCelable)
                .setNegativeString(negativeString)
                .setNegativeListener(dialogInterface)
                .setPositiveString(positiveString)
                .setPositiveListener(positiveDialogInterface)
                .creat()
                .show(manage,tag);
```
这个我在项目里做了封装，可以直接调用方法
```
DialogPakageUtils.getDefaultDialog(getSupportFragmentManager(), "0", "提示", "世界正在爆炸，请上天!",false, "否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }, "是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"上天完成!",Toast.LENGTH_SHORT).show();
                    }
                });
```
* 完全自定dialog:这里重写的onCreateView()方法，返回自定义布局或View
```
 CustomDialog customDialog = new CustomDialog.Builder()
                .setHasTitle(false)//是否显示自定义布局上的title空间
                .setCancelable(true)//点外部控件是否让dialog消失
                .setResId(reslayout)//设置布局id
                .setView(view)//设置view
                .setGravity(gravity)//控制dialog出现的位置
                .setTheme(animationStyle)//想要实现的动画xml资源
                .setDialogWidth(width)//dialog宽度
                .setDialogHeight(height)//dialog高度
                .setDimAmount(dimAmount)//dialog背景遮罩透明度
                .setIsTransparentDialog(isFullScreen)//dialog是否全屏
                .creat();//创建dialog对象
        customDialog.show(manage,tag);
```
* 列表dialog：为了使其有最大的开放性，我没有再里面内置RecycleView控件，我只封装了一个Adapter，实现理念继承了BaseRecyclerViewAdapterHelper,自定义adapter
或则继承里面的adapter都可以！
```
CustomDialog dialog = new CustomDialog.Builder()
                .setHasTitle(false)
                .setCancelable(cancelable)
                .setIsListDialog(true)
                .setListView(listView)//设置RecycleView
                .setBaseAdapter(adapter)//设置Adapter
                .setTheme(animationStyle)
                .setDialogWidth(dialogWidth)
                .setDialogHeight(dialogHeight)
                .setGravity(gravity)
                .setDimAmount(0.3f)
                .creat();
        dialog.show(manage,tag);
```
具体使用
===
请参考项目里的Demo

推荐阅读
===
[ShowTimeForKotlin](https://github.com/HappyGhostz/ShowTimeForKotlin)：MVP模式+dagger2+ Glide +Fresco+ Retrofit+OkHttp+GreenDao + RxJava + Kotlin
功能比较多，欢迎Star

[我的博客](https://blog.csdn.net/zcpHappy)：谢谢支持！
