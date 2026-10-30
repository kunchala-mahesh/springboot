# Spring Boot Jenkins Freestyle CI/CD Practice Project

A lightweight Spring Boot application designed specifically for practicing **Jenkins Freestyle Projects**.

---

## 📁 Project Structure

```text
spring-boot-jenkins-practice/
├── .mvn/wrapper/
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   ├── DemoApplication.java
│   │   │   └── controller/
│   │   │       └── HelloController.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/example/demo/
│           ├── DemoApplicationTests.java
│           └── controller/
│               └── HelloControllerTest.java
├── .gitignore
├── mvnw / mvnw.cmd
├── pom.xml
└── README.md
```

---

## 🚀 Available Endpoints

When running locally (`http://localhost:8080`):
- `GET /` — Returns welcome text: `"Welcome to Spring Boot CI/CD Demo Application!"`
- `GET /api/hello?name=DevOps` — Returns greeting JSON with a timestamp.
- `GET /api/health` — Returns application status `{"status": "UP", "app": "spring-boot-jenkins-practice"}`.

---

## 🛠️ Local Verification

Run the following in the project root:

```bash
# Run unit tests (generates target/surefire-reports/*.xml)
mvn clean test

# Build executable JAR (generates target/spring-boot-jenkins-practice-1.0.0.jar)
mvn clean package

# Run the application
java -jar target/spring-boot-jenkins-practice-1.0.0.jar
```

---

## 🏗️ Jenkins Freestyle Project Step-by-Step Setup

### Step 1: Create a New Item
1. Open Jenkins Dashboard (`http://localhost:8080`).
2. Click **New Item**.
3. Enter item name: `spring-boot-practice-job`.
4. Select **Freestyle project** and click **OK**.

---

### Step 2: Source Code Management (SCM)
Choose one of the following based on where your code lives:

#### Option A: Remote Git (GitHub / GitLab / Bitbucket)
1. Push this folder to your Git repository:
   ```bash
   git remote add origin <YOUR_REPO_URL>
   git branch -M main
   git push -u origin main
   ```
2. In Jenkins SCM, select **Git**.
3. **Repository URL**: `<YOUR_REPO_URL>`
4. **Credentials**: Add credentials if repository is private.
5. **Branch Specifier**: `*/main` (or `*/master`).

#### Option B: Local Repository (Direct filesystem practice)
If Jenkins is running on the same machine:
- Select **Git** and use the local path:
  `file:///C:/Users/gopes/.gemini/antigravity/scratch/spring-boot-jenkins-practice`

---

### Step 3: Configure Build Triggers (Optional)
- **Poll SCM**: e.g., `H/5 * * * *` (checks repository every 5 minutes for new commits).
- **GitHub hook trigger for GITScm polling**: For webhook-based builds.

---

### Step 4: Add Build Step
Click **Add build step**:

#### Recommended Option: "Invoke top-level Maven targets"
*(Requires Maven configured under **Manage Jenkins > Tools > Maven**)*
- **Maven Version**: Select your configured Maven (e.g., `Maven 3.9`).
- **Goals**:
  ```text
  clean package
  ```

#### Alternative Option (Execute Shell / Windows Batch)
- If Jenkins runs on **Windows**:
  - Select **Execute Windows batch command**:
    ```cmd
    call mvn clean package
    ```
- If Jenkins runs on **Linux / Docker**:
  - Select **Execute shell**:
    ```bash
    mvn clean package
    ```

---

### Step 5: Post-build Actions
Click **Add post-build action** to track test results and artifacts:

1. **Publish JUnit test result report**:
   - **Test report XMLs**:
     ```text
     **/target/surefire-reports/*.xml
     ```
   - *This enables Jenkins to generate test result trend charts, test summaries, and track regressions.*

2. **Archive the artifacts**:
   - **Files to archive**:
     ```text
     **/target/*.jar
     ```
   - *This saves the built JAR on Jenkins for downloading or deploying in subsequent steps.*

---

### Step 6: Run and Verify
1. Click **Save**.
2. Click **Build Now** on the left menu.
3. Click on the build number (e.g., `#1`) and view **Console Output** to verify the Maven build log.
4. Check the job status:
   - ✅ **Blue/Green Ball**: Successful build and test execution.
   - 📊 **Test Result Trend**: View JUnit results (4 tests passed).
   - 📦 **Last Successful Artifacts**: Download `spring-boot-jenkins-practice-1.0.0.jar`.
