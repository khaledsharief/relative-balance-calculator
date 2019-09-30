**Relative Account Balance Calculator** is an application to print the relative account balance (positive or negative) and the number of transactions that were considered, in a given time frame from a list of transaction records. 
Relative Account Balance is calculated using the following guidelines:

 1. The relative account balance is the sum of funds that were
    transferred to / from an account in a given time frame, it does not
    account for funds that were in that account prior to the time frame.
 2. Reversal transactions are omitted as part of the calculation, even
    if the reversing transaction is outside the given time frame.

### Technologies/Libraries Used
Java8, Opencsv, Maven, JUnit 5, JaCoCo

### Design

**Relative Account Balance Calculator** java application consists of two main components:

1. Financial transactions parser - is a singleton that uses Opencsv library to parse the contents of the input csv file to a list of TransactionRecords
2. Balance calculator - does the business processing and calculates the relative balance by taking the inputs: AccountID, fromDate, toDate

Following are the steps taken to calculate the relative balance

```bash
1. All 'PAYMENT' type transactions in the given time frame and for the given accountID are filtered
2. All 'REVERSAL' type transactions for the given accountID are filtered, irrespective of the time frame
3. If there are REVERSAL transactions found in the input list of transactions for the given accountID, then the related PAYMENT transaction from the list of PAYMENT transactions in Step 1 is removed
4. The relative balance is calculated, postive or negative, based on whether the input accountID was credited or debited
```

### Build from source

Clone the Relative Account Balance Calculator source code

```bash
git clone https://github.com/khaledsharief/relative-balance-calculator.git
```

Get into the root directory of the application

```bash
cd relative-balance-calculator
```

Build the Relative Account Balance Calculator from the source

```bash
mvn clean install
```

Get into the target directory of the application where the jar file is bundled

```bash
cd target
```

Run the maven shaded jar

```bash
java -jar BalanceCalculator-0.0.1-SNAPSHOT-shaded.jar
```

The jar will run and ask for user inputs as shown below:

```bash
Enter number account ID :
ACC998877
Enter the from date in dd/MM/yyyy HH:mm:ss format: 
20/10/2018 18:00:00
Enter the to date in dd/MM/yyyy HH:mm:ss format: 
20/10/2018 19:45:00
```

Additional Notes:

```bash
1. Libraries used: 
	a. Opencsv - library to easily read csv files and populate its values into java objects.
	b. lombok - library to generate boiler plate code
	c. JUnit 5 - library for writing JUnits
```
```bash
2. Maven plugins used:
	a. jacoco-maven-plugin - plugin to generate code coverage report based on the unit tests written
	b. maven-shade-plugin - plugin to bundle the java application as a single jar file containing all dependant jar files
```
