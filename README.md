[![](https://jitpack.io/v/DmitryOvchinikov/APK-Installer.svg)](https://jitpack.io/#DmitryOvchinikov/APK-Installer)

## Usage
A library to install an APK programmatically via a direct link to a download.
The library will work with only with direct links to a download, or if the direct link was substituted by the server (301 Moved Permanently, 307, 308).

## Integration  
  Add it in your root build.gradle at the end of repositories:
```css  
   allprojects {  
      repositories {  
         ...  
         maven { url 'https://jitpack.io' }  
      }  
   }  
```  
Add the dependency:
```css  
   dependencies {  
	     implementation 'com.github.DmitryOvchinikov:APK-Installer:1.0.2'
   }  
```  

## How to Use
**1.** Create an instance of the installer and execute it:
```Java
    new APKInstall(context, activity, YourProgressBar, YourTextView, "YourAPKDirectDownloadLink").execute();
```

<img src="https://github.com/DmitryOvchinikov/APK-Installer/blob/master/library_demo.gif" width="200" width="120" />

## License   
  
```  
Copyright 2020 Dmitry Ovchinikov  
  
Licensed under the Apache License, Version 2.0 (the "License");  
you may not use this file except in compliance with the License.  
You may obtain a copy of the License at  
  
https://github.com/DmitryOvchinikov/AdvancedEditText/blob/master/LICENSE  
  
Unless required by applicable law or agreed to in writing, software  
distributed under the License is distributed on an "AS IS" BASIS,  
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  
See the License for the specific language governing permissions and  
limitations under the License.  
```
