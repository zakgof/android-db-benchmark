## android-db-benchmark

Benchmarking some android data storage frameworks:

- [paper](https://github.com/pilgr/Paper)
- [hawk](https://github.com/orhanobut/hawk)
- [nitrite aka NO2](https://github.com/nitrite/nitrite-java)
- [room](https://developer.android.com/topic/libraries/architecture/room)
- [velvetdb](https://github.com/zakgof/velvetdb)


### Test operations:

- **Write**: insert 1000 person records with random data
- **Read**: get 1000 random persons using lastName as the key


### Interactive mode:

- install app on your phone
- click a button to run a specific test
- observe the report (time calculated using `System.currentMillis()`)

### Benchmark mode:

- Run as android Jetpack instrumented benchmark

## Results:
