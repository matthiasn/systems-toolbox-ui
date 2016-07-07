(ns matthiasn.systems-toolbox-ui.math-test
  (:require [clojure.test :refer :all]
            [matthiasn.systems-toolbox-ui.charts.math :as m]))

(deftest increment-test
  (testing "returns number of intervals that can be displayed properly"
    (is (= (m/default-increment-fn 0) 1))
    (is (= (m/default-increment-fn 80) 25))
    (is (= (m/default-increment-fn 150) 25))
    (is (= (m/default-increment-fn 320) 50))
    (is (= (m/default-increment-fn 700) 100))
    (is (= (m/default-increment-fn 1200) 250))
    (is (= (m/default-increment-fn 88888) 25000))))

(deftest interquartile-range-test
  (testing "IQR of range 0 to 99 is 50"
    (is (= (m/interquartile-range (range 100)) 50)))
  (testing "IQR of empty seq is nil"
    (is (nil? (m/interquartile-range []))))
  (testing "IQR of seq with a single val is zero"
    (is (zero? (m/interquartile-range [100])))))
