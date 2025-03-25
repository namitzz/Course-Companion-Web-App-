# Instructions For Running Group 77 Project

## MySQL Preparation:
- Make sure that a localhost server in MySQL is created called co2123db -
  - Make sure that the Hostname is set to "127.0.0.1" and the Port is "3306"
  - Inside this server create a database with the same name co2123db. This can be done with the following SQL code:
    ```
    create database co2123db;
    use co2123db;
    ```
- There should be a user inside MySQL created called "co2123" with the password set to "password"
- Dummy data to be added to the database for Badges and Search:
  
  ``` 
  INSERT INTO Course (title, description) VALUES
  ('Java Programming', 'Learn Java from basics to advanced concepts.'),
  ('Python for Beginners', 'A comprehensive introduction to Python programming.'),
  ('Web Development with HTML, CSS, and JavaScript', 'Build stunning websites from scratch.'),
  ('Spring Boot and Microservices', 'Learn how to develop scalable backend applications.'),
  ('Data Structures and Algorithms', 'Master data structures and algorithms for coding interviews.'),
  ('Machine Learning Fundamentals', 'Introduction to ML concepts and practical applications.'),
  ('Database Management with MySQL', 'Learn relational database management and SQL.'),
  ('Cybersecurity Essentials', 'Understand cybersecurity principles and best practices.'),
  ('Mobile App Development with Flutter', 'Create cross-platform mobile applications using Flutter.'),
  ('React and Redux for Frontend Development', 'Build modern frontend applications with React and Redux.'),
  ('Cloud Computing with AWS', 'Learn cloud computing and AWS services.'),
  ('DevOps and CI/CD Pipelines', 'Automate software deployment using DevOps practices.'),
  ('Artificial Intelligence and Deep Learning', 'Explore AI and neural networks with hands-on projects.'),
  ('Blockchain and Cryptocurrency Technologies', 'Understand blockchain technology and its applications.'),
  ('Ethical Hacking and Penetration Testing', 'Learn ethical hacking techniques and penetration testing.'),
  ('Kotlin for Android Development', 'Build modern Android apps using Kotlin.'),
  ('C++ Programming for Game Development', 'Learn C++ with a focus on game development.'),
  ('Big Data Analytics with Apache Spark', 'Process large datasets using Apache Spark.'),
  ('UI/UX Design with Figma and Adobe XD', 'Design stunning user interfaces and experiences.'),
  ('Rust Programming for System Development', 'Explore Rust for safe and efficient system programming.'),
  ('Programming for System Development', 'Explore Rust for safe and efficient system programming.'); 
  ```
    ```
    INSERT INTO search_entity (id, name, description) VALUES
    (1, 'Java Programming', 'Learn Java from basics to advanced concepts.'),
    (2,'Python for Beginners', 'A comprehensive introduction to Python programming.'),
    (3,'Web Development with HTML, CSS, and JavaScript', 'Build stunning websites from scratch.'),
    (4,'Spring Boot and Microservices', 'Learn how to develop scalable backend applications.'),
    (5,'Data Structures and Algorithms', 'Master data structures and algorithms for coding interviews.'),
    (6,'Machine Learning Fundamentals', 'Introduction to ML concepts and practical applications.'),
    (7,'Database Management with MySQL', 'Learn relational database management and SQL.'),
    (8,'Cybersecurity Essentials', 'Understand cybersecurity principles and best practices.'),
    (9,'Mobile App Development with Flutter', 'Create cross-platform mobile applications using Flutter.'),
    (10,'React and Redux for Frontend Development', 'Build modern frontend applications with React and Redux.'),
    (11,'Cloud Computing with AWS', 'Learn cloud computing and AWS services.'),
    (12,'DevOps and CI/CD Pipelines', 'Automate software deployment using DevOps practices.'),
    (13,'Artificial Intelligence and Deep Learning', 'Explore AI and neural networks with hands-on projects.'),
    (14,'Blockchain and Cryptocurrency Technologies', 'Understand blockchain technology and its applications.'),
    (15,'Ethical Hacking and Penetration Testing', 'Learn ethical hacking techniques and penetration testing.'),
    (16,'Kotlin for Android Development', 'Build modern Android apps using Kotlin.'),
    (17,'C++ Programming for Game Development', 'Learn C++ with a focus on game development.'),
    (18,'Big Data Analytics with Apache Spark', 'Process large datasets using Apache Spark.'),
    (19,'UI/UX Design with Figma and Adobe XD', 'Design stunning user interfaces and experiences.'),
    (20,'Rust Programming for System Development', 'Explore Rust for safe and efficient system programming.'),
    (21,'Programming for System Development', 'Explore Rust for safe and efficient system programming.');
    ```
## Intelli J / IDE Preparation:
- Ensure that Gradle is installed (The IDE may prompt you to download it if it isn't installed already)
- Connect the database to your IDE
- Run the Application.java file

## Browser Preparation:
- Visit this link in browser to enter into the site: http://localhost:8080/