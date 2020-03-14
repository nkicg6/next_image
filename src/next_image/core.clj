(ns next_image.core
  (:require [seesaw.core :as sc]
            [clojure.java.io :as io]))

;; left off on line 182 of https://gist.github.com/daveray/1441520 where it starts to get useful 
(sc/native!)
;; list files

(def next-image-state
  "holds all state for app"
  (atom {:todo-files "" :done-files ""}))

(defn update-state-field!
  [state-atom kw thing]
  (swap! state-atom assoc kw thing))

(defn get-files
  [dir endswith]
  "Selects files in a dir ending with endswith. Removes files classified as hidden by java file io api"
  (->> dir
       io/file
       file-seq
       (filter #(.isFile %))
       (filter #(complement (.isHidden %)))
       (map #(.getAbsolutePath %))
       (filter #(.endsWith % endswith))
       sort))

;;;; repl play and development

(defn add-test-files
  []
  ["file1" "f2" "f333"])

(update-state-field! next-image-state :todo-files (add-test-files))

@next-image-state
