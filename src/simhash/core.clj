(ns simhash.core)

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

(defn hamming-distance
  ^{:doc "Measures the minimum number of substitutions required to
   change one string into the other, or the number of errors
   that transformed one string into the other"}
  [^String s1 ^String s2] )
                                             