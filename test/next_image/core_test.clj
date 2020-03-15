;; test code
(ns next-image.core-test
  (:require [clojure.test :refer :all]
            [next-image.core :as core]))

(deftest update-atom
  (let [todos ["f1" "f2" "f3"]
        dones ["d1" "d2"]]
    (core/update-todo-done-field! core/next-image-state :todo-files todos)
    (is (= todos (:todo-files @core/next-image-state)))
    (is (= (count todos) (:todo-files-count @core/next-image-state)))
    (core/update-todo-done-field! core/next-image-state :done-files dones)
    (is (= dones (:done-files @core/next-image-state)))
    (is (= (count dones) (:done-files-count @core/next-image-state)))))

