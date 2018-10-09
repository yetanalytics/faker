(ns faker.phone-number
  "Generate fake phone numbers."
  (:require [clojure.string :as string]
            [random-seed.core :as rs]))

(def ^{:private true} formats
  ["###-###-####",
   "(###)###-####",
   "1-###-###-####",
   "###.###.####",
   "###-###-####",
   "(###)###-####",
   "1-###-###-####",
   "###.###.####",
   "###-###-#### x###",
   "(###)###-#### x###",
   "1-###-###-#### x###",
   "###.###.#### x###",
   "###-###-#### x####",
   "(###)###-#### x####",
   "1-###-###-#### x####",
   "###.###.#### x####",
   "###-###-#### x#####",
   "(###)###-#### x#####",
   "1-###-###-#### x#####",
   "###.###.#### x#####"])

(defn phone-numbers []
  "Lazy sequence of random phone numbers."
  (repeatedly
    (fn []
      (string/replace (rs/rand-nth formats)
                      #"#"
                      (fn [_] (str (rs/rand-int 10)))))))

