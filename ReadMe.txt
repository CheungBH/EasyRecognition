1. Basic functions:
Add images to the album and download the image store
Register a face image
Perform image recognition and position screening to analyze facial features (show likelihood results)
Data query (edit content text for local database storage, query or delete data)


2. How does the FaceDetect (ArcFaceDemo) project work?
?
? Download androidStudio development tools (latest stable version), SDK and other environments automatically match the installation. Open andoidStudio to import the project, right click on the project and select Run to run. If you are not configuring a running device, you can select Run Configuration to configure it. Successful operation, that is, directly installed to the mobile device.
  The SDK version is 26
  The dependency libraries are shown below:
  dependencies {
      compile fileTree(include: ['*.jar'], dir: 'libs')
      compile files('libs/facedetection.jar')
      compile files('libs/facerecognition.jar')
      compile files('libs/facetracking.jar')
      compile files('libs/ageestimation.jar')
      compile files('libs/genderestimation.jar')
      compile 'com.guo.android_extend:android-extend:1.0.6'
      compile 'com.android.support:support-v4:26.1.0'
      compile 'com.github.bumptech.glide:glide:3.6.1'
      compile 'com.yanzhenjie:album:1.0.0'
      compile 'com.android.support:appcompat-v7:26.1.0'
      compile 'com.android.support:recyclerview-v7:26.1.0'
      compile 'com.android.support:design:26.1.0'
}

3. Install the app successfully. Instructions
(1) Click on the desktop FaceDetect to enter the main menu, including 4 main menus: add pictures, register face, face recognition, data search
(2) Add pictures function:
Add a picture (you must first name the picture, select it from the local image resource of the phone, and after successful, the list of the cell will display the picture with successful addition)
Search for images (search for keywords in the search field, click search to filter local image resources with the same name)
The picture can be swiped, downloaded, and deleted (click on the grid to view it, and at the bottom of the picture there is a download and delete button)
(3) Register face and recognition: You need to register the face resource library before you can identify the operation and select the image upload. Facerecognition packaged registration and identification method
(4) Data search (query, add, update, delete) conversion function is the operation of adding, deleting, changing, etc. of the database table.