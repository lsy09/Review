package java8inaction.chap7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Parallel {

    public static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

    public static void main(String[] args) {
//        System.out.println("Iterative Sum done in: " + measurePerf(Parallel::iterativeSum, 10_000_000L) + " msecs");
//        System.out.println("Sequential Sum done in: " + measurePerf(Parallel::sequentialSum, 10_000_000L) + " msecs");
//        System.out.println("parallelSum Sum done in: " + measurePerf(Parallel::parallelSum, 10_000_000L) + " msecs");
//        System.out.println("rangedSum done in: " + measurePerf(Parallel::rangedSum, 10_000_000L) + " msecs");
//        System.out.println("Parallel range sum done in: " + measurePerf(Parallel::parallelRangedSum, 10_000_000L) + " msecs");
//        System.out.println("sideEffectSum sum done in: " + measurePerf(Parallel::sideEffectSum, 10_000_000L) + " msecs");
        System.out.println("sideEffect parallel sum done in: " + measurePerf(Parallel::sideEffectParallelSum, 10_000_000L) + " msecs");
    }

    public static long sequentialSum(long n){
        return Stream.iterate(1L, i -> i+1)
                     .limit(n)
                     .reduce(0L, Long::sum);
    }

    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 0; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public static long parallelSum(long n){
        return Stream.iterate(1L, i -> i+1)
                     .limit(n)
                     .parallel()
                     .reduce(0L, Long::sum);
    }

    /**
     * 예제 7-1 n개의 숫자를 더하는 함수의 성능 측정
     */
    public static <T, R> long measurePerf(Function<T, R> f, T input) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            R result = f.apply(input);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + result);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }

    // 언박싱과 관련한 오버헤드가 얼마나될까? 순차 스트림을 처리하는 시간을 측정
    public static long rangedSum(long n){
        return LongStream.rangeClosed(1, n)
                         .reduce(0L, Long::sum);
    }

    //새로운 버전에 병렬 스트림을 적용
    public static long parallelRangedSum(long n){
        return LongStream.rangeClosed(1, n)
                         .parallel()
                         .reduce(0L, Long::sum);
    }

    //n까지의 자연수를 더하면서 공유된 누적자를 바꾸는 프로그램을 구현
    public static long sideEffectSum(long n){
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    public static class Accumulator {
        public long total = 0;
        public void add(long value){ total += value; }
    }


    //스트림을 병렬로 만들어서 어떤 문제가 일어나는지 확인
    public static long sideEffectParallelSum(long n){
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;

    }

}
