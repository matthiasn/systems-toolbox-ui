(ns matthiasn.systems-toolbox-ui.counter-test
  (:require [clojure.test :refer :all]
            [matthiasn.systems-toolbox-ui.test-server :as ts]
            [clj-webdriver.taxi :as tx]))

(deftest open-page
  (testing "open counter example, interact, UI should change"
    (testing "click first counter thrice, should be 5"
      (tx/click ".counters div:nth-of-type(1) button:nth-of-type(2)")
      (tx/click ".counters div:nth-of-type(1) button:nth-of-type(2)")
      (tx/click ".counters div:nth-of-type(1) button:nth-of-type(2)")
      (Thread/sleep 100)
      (is (= "5" (tx/text (tx/find-element {:css ".counters div:nth-of-type(1) h1"})))))

    (testing "add counter, click thrice, should be 3"
      (tx/click ".counters button:nth-of-type(2)")
      (Thread/sleep 100)
      (tx/click ".counters div:nth-of-type(4) button:nth-of-type(2)")
      (tx/click ".counters div:nth-of-type(4) button:nth-of-type(2)")
      (tx/click ".counters div:nth-of-type(4) button:nth-of-type(2)")
      (Thread/sleep 100)
      (is (= "3" (tx/text (tx/find-element {:css ".counters div:nth-of-type(4) h1"})))))

    (testing "fourth counter exists, click remove, should be gone"
      (is (tx/find-element {:css ".counters div:nth-of-type(4)"}))
      (tx/click ".counters button:nth-of-type(1)")
      (Thread/sleep 100)
      (is (not (tx/find-element {:css ".counters div:nth-of-type(4)"}))))))

(use-fixtures :once ts/once-fixture)
