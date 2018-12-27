### PrestoQ test app

Project status: [![Build status](https://ci.appveyor.com/api/projects/status/mqj5248ok29w1l4v?svg=true)](https://ci.appveyor.com/project/ethrbunny/prestoqapp)

Master branch status: [![Build status](https://ci.appveyor.com/api/projects/status/mqj5248ok29w1l4v/branch/master?svg=true)](https://ci.appveyor.com/project/ethrbunny/prestoqapp/branch/master)


There are 2 components:
- harness
  - test app
- parser
  - take a line of 'product description' and return a **ProductRecord** object
  
To build:
(from project root): *./gradlew clean build test*

To run:
(from project root): *java -jar harness/build/libs/harness.jar input-sample.txt*

