# Goal

To produce a simple program with which two users can play chess against one another.

Setup

1. Clone Repo
2. Ensure your machine has java and ant installed
3. From here, there are two methods of running the project...
   a. Run `ant resolve` to pull in ivy dependencies and generate lib directory. Then `ant main` to produce a local jar and run program
   b. Navigate to maven/chessapp/src and then run `mvn package` - this will run all tests and generate a jar which you can run yourself.
   This is also beneficial in that it will give you things like code coverage reports.

Helper Commands

1. Prettier should run on save. To run it manually for Java, go to https://github.com/jhipster/prettier-java
2. `ant junit` is a way to run tests manually - alongside running `mvn package`
