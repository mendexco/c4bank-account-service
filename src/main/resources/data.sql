-- Insert into address table
INSERT INTO address (city, country, neighborhood, number, postal_code, state, street, id)
VALUES ('São Paulo', 'Brazil', 'Vila Mariana', '123', '09990412', 'São Paulo', 'Rua Vergueiro',
        '01JA3XGFFY63JXGCFJREDAG7ZC');

-- Insert into account table
INSERT INTO account (id, account_number, address_id, balance, name, phone, status, tax_identifier)
VALUES ('01JA3XGFHGFDXSNAPVM5DDH25J', '000000-1', '01JA3XGFFY63JXGCFJREDAG7ZC', 0.00, 'André Cocão', '11988885555',
        'ACTIVE', '55599944471');