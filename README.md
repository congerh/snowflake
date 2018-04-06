# Snowflake

A Java program for generating unique ID numbers based on Twitter's [Snowflake](https://github.com/twitter/snowflake)

## ID is composed of:
* time - 41 bits (millisecond precision w/ a custom epoch gives us 69 years)
* configured machine id - 10 bits (5 bits datacenterId + 5 bits workerId) - gives us up to 1024 machines
* sequence number - 12 bits - rolls over every 4096 per machine (with protection to avoid rollover in the same ms)
## Usage
    IdGenerator.getInstance().generateId();