(ns simhash.core-test
  (:use clojure.test
        simhash.core))

(deftest md5-test
  (testing "Hashing strings with MD5"
    (let [test-string "The quick brown fox jumps over the lazy dog"]
    (is (= "9e107d9d372bb6826bd81d3542a419d6"
           (md5 test-string))))))
