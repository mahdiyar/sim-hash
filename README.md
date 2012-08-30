# Simhash Near Duplicate Detection

Clojure implementation of Charikar's simhash algorithm for identification of similar documents.

http://www.cs.princeton.edu/courses/archive/spring04/cos598B/bib/CharikarEstim.pdf

A nice Erlang implementation here

http://blog.jebu.net/2009/08/simhashing-in-erlang-beauty-with-binary-comprehension/

## About

The calculation of the hash is performend in the following way:

* Document is split into tokens (words for example) or super-tokens (word tuples)
* Each token is represented by its hash value; a traditonal hash function is used
* Weights are associated with tokens
* A vector `V` of integers is initialized to `0`, length of the vector corresponds to the desired hash size in bits
* In a cycle for all token's hash values (`h`), vector `V` is updated:
  * i<sup>th</sup> element is decreased by token's weight if the i<sup>th</sup> bit of the hash `h` is `0`, otherwise
  * i<sup>th</sup> element is increased by token's weight if the i<sup>th</sup> bit of the hash `h` is `1`
* Finally, signs of elements of `V` corresponds to the bits of the final fingerprint

## License

Copyright © 2012 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
