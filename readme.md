# Service

## 一、Service基础

服务Service 是Android 的四大组件之二，常用在看不见页面的高级场合。既然Android 有系统服务，App 也可以有自己的服务。Service 与Activity 相比，不同之处在于没有对应的页面，相同之处在于有生命周期。要想用好服务，就要探究其生命周期。

下面是Service 与生命周期有关的方法说明。

- onCreate：创建服务。

- onStart：开始服务， Android2.0 以下版本使用，现已废弃。

- onStartCommand：开始服务， Androi也.0 及以上版本使用。该函数的返回值说明见下表

|         返回值类型         |                          返回值说明                          |
| :------------------------: | :----------------------------------------------------------: |
|        START_STICKY        | 粘性的服务。如果服务进程被杀掉，就保留服务的状态为开始状态，但<br/>不保留传送的Intent 对象。随后系统尝试重新创建服务，由于服务状态<br/>为开始状态，因此创建服务后一定会调用onStartCommand 方法。如果<br/>在此期间没有任何启动命令传送给服务， 参数Intent 就为空值. |
|      START_NOT_STICKY      | 非粘性的服务。使用这个返回值时，如果服务被异常杀掉，系统就不会<br/>自动重启该服务 |
|   START_REDELNER_INTENT    | 重传Intent 的服务。使用这个返回值时，如果服务被异常杀掉，系统就<br/>会自动重启该服务，并传入Intent 的原值 |
| START_STICKY_COMPATIBILITY |    START_STICKY 的兼容版本，不保证服务被杀掉后一定能重启     |

- onDestroy：销毁服务。

- onBind：绑定服务。

- onRebind：重新绑定。该方法只有当上次onUnbind 返回回e 的时候才会被调用。

- onUnbind：解除绑定。返回值为true 表示允许再次绑定，再绑定时调用onRebind 方法；返回值为false 表示只能绑定一次，不能再次绑定，默认为false。

1. 启动服务可通过如下代码启动：

```java
Intent intent;
intent = new Intent(MainActivity.this,MyService.class);
startService(intent);
```

2. 停止服务使用如下代码：

```java
Intent intent;
intent = new Intent(MainActivity.this,MyService.class);
stopService(intent);
```

3. 绑定服务可以用 `public boolean bindService(android.content.Intent service,android.content.ServiceConnection conn,int flags)`进行绑定，第一个参数是Intent对象，第二个是ServiceConnection接口的实例化对象，最后一个为标志位。本Demo中 MainActivity直接实现了ServiceConnection接口。