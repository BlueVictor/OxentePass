package com.oxentepass.oxentepass.service.outrasInterfaces;

import com.oxentepass.oxentepass.entity.Tag;

public interface TagManipulacao {
    public void adicionarTagExistente(long idObjBase, long idTag); // Adição de Tag existente
    public void adicionarTagNova(long idObjBase, Tag tag);    // Criação de nova Tag
    public void removerTag(long idObjBase, long idTag);
}
