(ns simhash.core
  (:import [java.security MessageDigest])
  (:import [JenkinsHash]))
                                      
;; Algorithm utils 

(defn- to-hash
  ^{:doc "Returns an hashed string"}
  [alg ^String s]
  (apply str
    (map (partial format "%02x")
      (.digest
        (doto (java.security.MessageDigest/getInstance alg)
          .reset
          (.update (.getBytes s)))))))

;; Partial functions

(def md5 (partial to-hash "md5"))

(def sha1 (partial to-hash "sha1"))

;; http://en.wikipedia.org/wiki/Hamming_distance

(def bool->int
  (fn [^java.lang.Boolean b]
    (if (false? b) 1 0)))

(defn jenkins-hash [^String s]
  (let [bytes (.getBytes s)]
    (JenkinsHash.)))

(defn hamming-distance
  ^{:doc "Measures the minimum number of substitutions required to
   change one string into the other, or the number of errors
   that transformed one string into the other.
   Hamming distance only counts substitutions, and so is only suitable for equal
   length strings"}
  [^String s1 ^String s2]
  (letfn [(zip [x y] (map vector x y))]
  (when (= (count s1) (count s2))
    (let [char-map (zip s1, s2)]
      (reduce + 
        (map #(bool->int %)
             (map (fn [[a b]] (= a b)) char-map)))))))

(defn sim-hash [^String s]
  (let [words (clojure.string/split s #"\W+")
        features (map #(.toLowerCase %) words)]
    (map #(md5 %) features)))


