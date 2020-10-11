<h2>glasswindow</h2>
<p>A simple job portal web application. It has two main APIs exposed. You can access them through<br> 1. GET(http://host:8081/jobs?skillKeywords=&location=) to get the active jobs based on skillKeywords and location.<br> 2. PUT(http://host:8081/jobs) to put a new job into the database. The application has a MVCS structure and I have tested the repository service layer, which has the primary functionalities. It has a simple static webpage attached(index.html) to help with the UI. </p>
<hr>
<h3>Environment setup</h3>
1. JDK 12.0.2<br>
2. Gradle 6.6.1<br>
3. MongoDB 4.4.0<br>
<hr>
<h3>Running the application</h3>
1. Run mongoDB server on your local machine(I have currently not used a mongo cluster, which is subject to change in future. I will updtae it here accordingly).<br>
2. Type ./gradlew bootrun.<br>
3. Open http://localhost:8081/index.html in your browser.<br>
<hr>
<h3>Authors</h3>
Manish Das(manishdasa100@gmail.com)<br>
