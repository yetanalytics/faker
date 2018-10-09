(ns faker.core-test
  (:use (faker name lorem phone-number internet address))
  (:require [faker.company :as company]
            [random-seed.core :as rs])
  (:use
     clojure.test
     [clojure.string :only (split)]))



(deftest test-name-generation
  (rs/set-random-seed! 888)
  
  (is (names))
  (is (= (first-name) "Maxine"))
  (is (= (last-name) "Hodkiewicz"))
  (is (= (prefix) "Ms."))
  (is (= (suffix) "I"))
  (let [many (map #(split % #" ") (take 10000 (names)))
        count-simple (count (filter #(= 2 (count %)) many))]
    (is (and (> count-simple 7000) (< count-simple 9000)))))

(deftest test-lorem-generation
  (rs/set-random-seed! 888)
  (is (= (take 5 (words)) ["cumque" "vel" "error" "sit" "qui"]))
  (is (= (take 5 (sentences)) 
         ["Expedita sint hic molestiae consequatur quo." 
          "Fuga excepturi quos ad qui molestias." 
          "Voluptatem et suscipit mollitia accusamus." 
          "Quaerat ut ea laboriosam." 
          "Culpa neque vel dolores hic."]))
  (is  (take 10 (paragraphs))))

(deftest test-phone-numbers
  (rs/set-random-seed! 888)
  (is (= (take 10 (phone-numbers))
         ["1-351-846-5948 x8365" 
          "(245)130-3580 x283" 
          "1-251-849-1584" 
          "880-774-5511" 
          "098-351-9471" 
          "240.204.6384 x2905" 
          "(003)320-0775 x10239" 
          "1-930-765-4376 x55552" 
          "1-762-617-7168" 
          "1-656-461-0658 x438"])))

(deftest test-company
  (rs/set-random-seed! 888)
  (is (= (company/suffix)
         "LLC"))
  (is (= (company/catch-phrase)
         "Implemented leading edge collaboration"))
  (is (= (company/bs)
         "revolutionize bleeding-edge systems"))
  (is (= (take 5 (company/names))
         ["Wiegand-Kassulke" 
          "Smitham, Huels and Mueller" 
          "Kulas-Goodwin" 
          "Labadie-Donnelly" 
          "Annabel Hartmann LLC"]))) 
          

(deftest test-internet
  (rs/set-random-seed! 888)
  (is (= (domain-suffix) "biz"))
  (is (= (domain-name) "maggiodeckow.info"))
  (is (= (domain-word) "okeefelarkin"))
  (is (= (user-name) "ramon.smitham"))
  (is (= (email) "linda@kulasgoodwin.uk"))
  (is (= (free-email) "emmalee.bergstrom@yahoo.com"))
  (is (= (take 5 (emails))
         ["devan.tromp@hayesframi.ca" 
          "valentine.dooley@yahoo.com" 
          "deja@terry.biz" 
          "brenda-carroll@gmail.com" 
          "elisabeth-mcclure@gmail.com"]))) 
          
(deftest test-address
  (rs/set-random-seed! 888)
  (is (= (zip-code) "35184-6594"))
  (is (= (us-state) "South Dakota"))
  (is (= (us-state-abbr) "VT"))
  (is (= (city-prefix) "West"))
  (is (= (city-suffix) "stad"))
  (is (= (street-suffix) "Mission"))
  (is (= (city) "East Israel"))
  (is (= (street-name) "Clotilde Harbors"))
  (is (= (secondary-address) "Apt. 358"))
  (is (= (street-address) "28332 Carson Station"))
  (is (= (uk-county) "Hertfordshire"))
  (is (= (uk-country) "Northern Ireland"))
  (is (= (uk-postcode) "GV5 8WB")))
