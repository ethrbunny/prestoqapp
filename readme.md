### PrestoQ test app

There are 2 components:
- harness
  - test app
- parser
  - take a line of 'product description' and return a **ProductRecord** object
  
To build:
(from project root): *./gradlew clean build test*

To run:
(from project root): *java -jar harness/build/libs/harness.jar input-sample.txt*

