(ns faker.name
  "Create fake data for person names"
  (:use
     [clojure.string :only [join]]
     faker.name-data)
  (:require [random-seed.core :as rs]))

(defn first-name
  "Create a fake person first name"
  []
  (rs/rand-nth first-names))

(defn last-name
  "Create a fake person last name"
  []
  (rs/rand-nth last-names))

(defn prefix
  "Create a fake person prefix, like in Mr., Mrs., etc."
  []
  (rs/rand-nth prefixes))

(defn suffix
  "Create a fake person suffix, like in Jr., Sr., etc."
  []
  (rs/rand-nth suffixes))

(defn- comb [& funs]
  (fn [] (join " " (map #(%) funs))))

(def ^{:private true} format-probs
  [[(comb prefix first-name last-name) 0.1]
   [(comb first-name last-name suffix) 0.2]
   [(comb first-name last-name) 1]])

(defn- one-name []
  (let [p (rand)]
    (some #(and (> (last %) p)
                ((first %)))
          format-probs)))

(defn names
  "Lazy sequence of random names"
  []
  (repeatedly one-name))
