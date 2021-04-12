## log(android lib)
Adapter design log component, depends on com.orhanobut:logger
### build
to turn on/off output BroadPOS log through logcat in release build, please add/delete `debuggable true` in buildTypes of .app build.gradle file and comment/uncomment class android.util.Log and class java.io.PrintStream in .app proguard-rules.pro.</br>
e.g. for BroadPOS-FDRC</br>
gradle location: `BroadPOS-FDRC\FDRC\fdrc.app\build.gradle`</br>
debuggable location: `android -> buildTypes -> release`</br>
proguard location: `BroadPOS-FDRC\FDRC\fdrc.app\proguard-rules.pro`</br>

Add `debuggable true` to open output BroadPOS log through logcat in release build. Delete it to close.</br>
Comment class android.util.Log and class java.io.PrintStream in proguard-rules.pro to open output BroadPOS log through logcat. Uncomment it to close.
