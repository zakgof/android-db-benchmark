## android-db-benchmark

Benchmarking some android data storage frameworks:

- paper
- hawk
- nitrite
- room
- velvetdb


### Test operations:

- insert 1000 person records with random data
- get 1000 random persons using lastName as the key


### Interactive mode:

- install app on your phone
- click a button to run a specific test
- observe the report (time calculated using `System.currentMillis()`)

### Benchmark mode:

- Run as android Jetpack instrumented benchmark

## Results:
