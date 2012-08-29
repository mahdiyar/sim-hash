(ns simhash.core)

;; Algorithm utils 

(defn- to-hash
  "Returns an hashed string"
  [alg ^String s]
  (apply str
    (map (partial format "%02x")
      (.digest
        (doto (java.security.MessageDigest/getInstance alg)
          .reset
          (.update (.getBytes s)))))))

(def md5 (partial to-hash "md5"))

(def sha1 (partial to-hash "sha1"))
