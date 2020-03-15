(ns next-image.core
  (:require [seesaw.core :as sc]))

;; left off on line 182 of https://gist.github.com/daveray/1441520 where it starts to get useful 
(sc/native!)
;; list files

(def next-image-state
  "holds all state for app"
  (atom {:todo-files nil
         :done-files nil
         :todo-files-count nil
         :done-files-count nil}))

(defn update-todo-done-field!
  "updates todo adn done lists. Also updates metadata like file lengths"
  [state-atom kw thing]
  (swap! state-atom assoc kw thing)
  (swap! state-atom assoc :todo-files-count (count (:todo-files @state-atom)))
  (swap! state-atom assoc :done-files-count (count (:done-files @state-atom))))
