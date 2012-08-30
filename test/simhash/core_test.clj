(ns simhash.core-test
  (:use clojure.test
        simhash.core))

(deftest hamming-distance-test
  (testing "hamming distance with strings"
    (is (= 1 (hamming-distance "cat" "can")))
    (is (= 2 (hamming-distance "cat" "cry")))))
     
(deftest md5-test
  (testing "Hashing strings with MD5"
    (let [test-string "The quick brown fox jumps over the lazy dog"]
    (is (= "9e107d9d372bb6826bd81d3542a419d6"
           (md5 test-string)))
    (is (= "e4d909c290d0fb1ca068ffaddf22cbd0"
           (md5 (format "%s." test-string))))
    (is (= "d41d8cd98f00b204e9800998ecf8427e"
           (md5 ""))))))
