(ns faker.company
  "Create fake company data"
  (:use
     [clojure.string :only (join)]
     faker.company-data)
  (:require [faker.name :as fkname]
            [random-seed.core :as rs]))

(defn suffix
  "Return a random company suffix, like Inc or Group."
  []
  (rs/rand-nth suffixes))

(defn- phrase [source]
  (join " " (map #(rs/rand-nth %) source)))

(defn catch-phrase
  "Return a random company catch phrase."
  []
  (phrase catch-phrase-words))

(defn bs
  "Return random company BS goals."
  []
  (phrase bs-words))

(def ^{:private true} formats
  [#(str (first (fkname/names)) " " (suffix))
   #(str (fkname/last-name) "-" (fkname/last-name))
   #(format "%s, %s and %s" (fkname/last-name) (fkname/last-name) (fkname/last-name))])


(defn names []
  "Lazy sequence of random company names"
  (repeatedly
    (fn []
      ((rs/rand-nth formats)))))
